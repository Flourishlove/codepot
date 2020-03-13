import torch
from torch import nn
import numpy as np
import matplotlib.pyplot as plt
%matplotlib inline

# 几个重要的参数说明
# input_size: 这是输入的特征维度，即每个时刻输入的x[t]到底有几维
# hidden_size: 这是隐层的节点数，和普通mlp一样，input_size*hidden_size决定了系数矩阵大小
# n_layers: 有多少层rnn纵向堆叠在一起
# sequence_lenth: 训练时候时间维度上的长度，即连续输入多少次
# batch_size: 每次训练的时候，会从数据集拿出一个batch，这个batch的大小是batch_size*sequence_lenth, batch_size代表的含义是每个batch里面有多少个sequence，每一个sequence其实是最小的训练单位


class RNN(nn.Module):
    def __init__(self, input_size, output_size, hidden_size, n_layers):
        super(RNN, self).__init__()

        self.hidden_size=hidden_size

        # define an RNN with specified parameters
        # batch_first means that the first dim of the input and output will be the batch_size
        self.rnn = nn.RNN(input_size, hidden_size, n_layers, batch_first=True)

        # last, fully-connected layer
        self.fc = nn.Linear(hidden_size, output_size)

    def forward(self, x, hidden):
        # x (batch_size, seq_length, input_size)
        # hidden (n_layers, batch_size, hidden_size)
        # r_out (batch_size, time_step, hidden_size)
        batch_size = x.size(0)

        # get RNN outputs
        r_out, hidden = self.rnn(x, hidden)
        # shape output to be (batch_size*seq_length, hidden_size)
        r_out = r_out.view(-1, self.hidden_size)

        # get final output
        output = self.fc(r_out)

        return output, hidden


#########################################
#####   测试建好的rnn维度是否正确
#########################################

# test that dimensions are as expected
test_rnn = RNN(input_size=1, output_size=1, hidden_size=10, n_layers=2)

# generate evenly spaced, test data pts
time_steps = np.linspace(0, np.pi, seq_length)
data = np.sin(time_steps)
data.resize((seq_length, 1))

test_input = torch.Tensor(data).unsqueeze(0) # give it a batch_size of 1 as first dimension
print('Input size: ', test_input.size())

# test out rnn sizes
test_out, test_h = test_rnn(test_input, None)
print('Output size: ', test_out.size())
print('Hidden state size: ', test_h.size())


#########################################
#####   开始训练
#########################################

# decide on hyperparameters
input_size=1
output_size=1
hidden_size=32
n_layers=1

# instantiate an RNN
rnn = RNN(input_size, output_size, hidden_size, n_layers)
print(rnn)

criterion = nn.MSELoss()
optimizer = torch.optim.Adam(rnn.parameters(), lr=0.01)

# 训练方法
def train(rnn, n_steps, print_every):

    # initialize the hidden state
    hidden = None

    for batch_i, step in enumerate(range(n_steps)):
        # defining the training data
        time_steps = np.linspace(step * np.pi, (step+1)*np.pi, seq_length + 1)
        data = np.sin(time_steps)
        data.resize((seq_length + 1, 1)) # input_size=1

        x = data[:-1]
        y = data[1:]

        # convert data into Tensors
        x_tensor = torch.Tensor(x).unsqueeze(0) # unsqueeze gives a 1, batch_size dimension
        y_tensor = torch.Tensor(y)

        # outputs from the rnn
        prediction, hidden = rnn(x_tensor, hidden)

        ## Representing Memory ##
        # make a new variable for hidden and detach the hidden state from its history
        # this way, we don't backpropagate through the entire history
        hidden = hidden.data

        # calculate the loss
        loss = criterion(prediction, y_tensor)
        # zero gradients
        optimizer.zero_grad()
        # perform backprop and update weights
        loss.backward()
        optimizer.step()

        # display loss and predictions
        if batch_i%print_every == 0:
            print('Loss: ', loss.item())
            plt.plot(time_steps[1:], x, 'r.') # input
            plt.plot(time_steps[1:], prediction.data.numpy().flatten(), 'b.') # predictions
            plt.show()

    return rnn

# train the rnn and monitor results
n_steps = 75
print_every = 15

trained_rnn = train(rnn, n_steps, print_every)