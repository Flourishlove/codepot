import torch
from torch import nn
import torch.nn.functional as F


# 测试NLLLoss和CrossEntropyLoss
test_input = torch.randn(3,3)
target = torch.tensor([0,2,1])

# 以下两者结果相等
loss1 = nn.NLLLoss()
loss1(torch.log(F.softmax(test_input, dim=1)), target)

loss2 = nn.CrossEntropyLoss()
loss2(test_input, target)


# 测试backward()函数
# 1. backward函数只能使用一次，要重新计算梯度，必须重新运行
# 2. z.backward()括号内不用加参数是因为z是一个标量；对于y,如果要运行y.backward(x),必须有参数，因为y不是标量
x = torch.randn(2,2, requires_grad=True)
y = x**2
print(y.grad_fn)    # grad_fn记录了y是如何得来
z = y.mean()
z.backward()
print(x.grad)




#########################################
#####   保存模型
#########################################

print("Our model: \n\n", model, '\n')
print("The state dict keys: \n\n", model.state_dict().keys())

# 方法一：直接存储模型参数
torch.save(model.state_dict(), 'checkpoint.pth')
# 读取参数，但是必须model的结构（节点和层数）和原模型完全一致才不会出错
model.load_state_dict(state_dict)

# 方法二：把模型参数和模型结构一起存储
checkpoint = {'input_size': 784,
              'output_size': 10,
              'hidden_layers': [each.out_features for each in model.hidden_layers],
              'state_dict': model.state_dict()}

torch.save(checkpoint, 'checkpoint.pth')

# 再写一个函数进行读取
def load_checkpoint(filepath):
    checkpoint = torch.load(filepath)
    model = fc_model.Network(checkpoint['input_size'],
                             checkpoint['output_size'],
                             checkpoint['hidden_layers'])
    model.load_state_dict(checkpoint['state_dict'])

    return model

model = load_checkpoint('checkpoint.pth')
print(model)
