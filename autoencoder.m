%X = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15];
%X = X';
tic;
X = zeros(16);
for i = 1:16
    X(i,i)=1;
end
%layer = reluLayer('Name','my_relu');
%layer = reluLayer();
%layer1 = relu();
%generateFunction(autoenc,'C:\Users\Devanshu\OneDrive\Data Mining\HW4\myfunc');
%bias = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
autoenc = trainAutoencoder(X,3,'EncoderTransferFunction','logsig','MaxEpochs',200000,'DecoderTransferFunction','satlin','L2WeightRegularization',0.0005,'SparsityProportion',0.66666666);
Z = encode(autoenc,X);
XReconstructed = predict(autoenc,X);
%use tied weights
mseError = mse(X-XReconstructed);
enc_weights = autoenc.EncoderWeights;
dec_weights = autoenc.DecoderWeights;
%autoenc = trainAutoencoder(X,5);
%Z = encode(autoenc,X);
toc;
%net = network(autoenc);

%options = trainingOptions('sgdm');
%convnet = trainNetwork(Z,layer,options);
%10084