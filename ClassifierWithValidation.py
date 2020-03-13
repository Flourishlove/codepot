from torch import nn, optim
import torch.nn.functional as F

class ClassifierWithVal(nn.Module):
    def __init__(self):
        super().__init__()
        self.fc1 = nn.Linear(784, 256)
        self.fc2 = nn.Linear(256, 128)
        self.fc3 = nn.Linear(128, 64)
        self.fc4 = nn.Linear(64, 10)

        self.dropout = nn.Dropout(p=0.2)

    def forward(self,x):
        x = x.view(x.shape[0], -1)

        x = self.dropout(F.relu(self.fc1(x)))
        x = self.dropout(F.relu(self.fc2(x)))
        x = self.dropout(F.relu(self.fc3(x)))

        x = F.log_softmax(self.fc4(x),dim=1)

        return x


#########################################
#####   开始训练
#########################################

model = ClassifierWithVal()
criterion = nn.NLLLoss()
optimizer = optim.Adam(model.parameters(), lr=0.01)

epochs = 30
train_losses, test_losses = [], []  # 每一个epoch都有一个train_loss和test_loss
for e in range(epochs):
    running_loss = 0
    for image, label in trainloader:
        optimizer.zero_grad()

        log_ps = model(image)
        loss = criterion(log_ps, label)
        loss.backward()
        optimizer.step()

        running_loss += loss.item()
    else:
        # 每一轮训练后输出以下
        test_loss = 0
        accuracy = 0

        # 这里只是要用validation set计算误差，不需要grad，所以torch.no_grad()
        with torch.no_grad():
            # model.eval()是将网络中dropout的概率设为0，即关闭dropout，因为我们在计算误差，需要全部节点
            model.eval()
            for image, label in testloader:
                log_ps = model(image)
                test_loss += criterion(log_ps, label)

                ps = torch.exp(log_ps)
                top_p, top_class = ps.topk(1, dim=1)
                equals = top_class == label.view(*top_class.shape)
                accuracy += torch.mean(equals.type(torch.FloatTensor))

        # 这里我们打开dropout，因为马上需要进入下一轮训练
        model.train()

        train_losses.append(running_loss/len(trainloader))
        test_losses.append(test_loss/len(testloader))

        print("Epoch: {}/{}.. ".format(e+1, epochs),
              "Training Loss: {:.3f}.. ".format(train_losses[-1]),
              "Test Loss: {:.3f}.. ".format(test_losses[-1]),
              "Test Accuracy: {:.3f}".format(accuracy/len(testloader)))

#########################################
#####   画出train和test loss
#########################################
%matplotlib inline
%config InlineBackend.figure_format = 'retina'

import matplotlib.pyplot as plt

plt.plot(train_losses, label='Training loss')
plt.plot(test_losses, label='Validation loss')
plt.legend(frameon=False)



#########################################
#####   使用模型进行预测
#########################################
import helper

# 要先把所有dropout关闭
model.eval()

dataiter = iter(testloader)
images, labels = dataiter.next()
img = images[0]
# Convert 2D image to 1D vector
img = img.view(1, 784)

# Calculate the class probabilities (softmax) for img
with torch.no_grad():
    output = model.forward(img)

ps = torch.exp(output)

# Plot the image and probabilities
helper.view_classify(img.view(1, 28, 28), ps, version='Fashion')



