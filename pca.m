X=csvread('C:\Users\Devanshu\OneDrive\Data Mining\HW3\alldata2.csv');
C=cov(X);
[V,D] = eig(C);

newD = zeros(364,1);
newV = zeros(364,364);

for i = 1:364
    newD(365-i)=D(i,i);
    newV(365-i)=V(i);
end

count = sum(newD);
count80 = zeros(364,1);
temp = 0;
for i = 1:364
    temp = temp + newD(i);
    count80(i) = temp; 
    if count80(i)>(count*0.8)
        break;
    end
end

plot(count80(1:i));
saveas(gcf,'cumulative_info.jpg');
plot(newD);
saveas(gcf,'eigen.jpg');
%plot(newD(1:60));
%saveas(gcf,'eigen60.jpg');

%[r,index] = sort(newD, 1, 'descend');
[U,S,V]=svd(C);
dominant = zeros(364,3);
dominant(:,1) = U(:,1);
dominant(:,2) = U(:,2);
dominant(:,3) = U(:,3);
plot(dominant);
domdata = dominant(1:176,1:3);
domctrl = dominant(177:364,1:3);
contour3(domdata);
saveas(gcf,'domdata.jpg');
contour3(domctrl);
saveas(gcf,'domctrl.jpg');

plot(1:176,domdata,'k-')
hold on
plot(177:364,domctrl,'k-')

scatter3(U(1:176,1),U(1:176,2),U(1:176,3));
hold on;
scatter3(U(177:364,1),U(177:364,2),U(177:364,3));

scatter(U(1:176,1),U(1:176,2));
hold on;
scatter(U(177:364,1),U(177:364,2));

