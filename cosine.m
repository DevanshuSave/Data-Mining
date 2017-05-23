
X=csvread('C:\Users\Devanshu\OneDrive\Data Mining\HW3\onlydata2.csv');
a=zeros(99,1);
s=zeros(99,1);
d=zeros(99,1);

normX = zeros(8560,1);
normC = zeros(8560,1);
for i = 1:8560
    normX(i) = norm(X(i),2);
end
for k = 2:100
    [idx,C,sumd,D] = kmeans(X,k,'Distance','cosine','Options',statset('UseParallel',1),'MaxIter',1000,'Replicates',1);
    
    incluster = zeros(k,1);
    betweencluster = zeros(k,1);
    count = zeros(k,1);
    %Incluster similarity
    for j = 1:8560
        i = idx(j);
        for m = 1:8560
            %temp = (dot(X(j),X(m)));
            temp = 1-(dot(X(j),X(m)))/(normX(j)*normX(m));
            incluster(i)=incluster(i)+temp;
            count(i)=count(i)+1;
        end
    end

    for i = 1:k
        incluster(i)=incluster(i)/count(i);
        %fprintf('%16.f',incluster(i));
        %disp(incluster(i));
    end

    s(k) = sum(incluster)/(k-1);

    %Between Cluster
    for j = 1:k
        for m = 1:k
            %temp = (dot(C(j),C(m)));
            temp = 1- (dot(C(j),C(m)))/(norm(C(j),2)*norm(C(m),2));
            betweencluster(j)=betweencluster(j)+temp;
        end
    end

    for i = 1:k
        incluster(i)=incluster(i)/k;
        %fprintf('%16.f',incluster(i));
        %disp(incluster(i));
    end

    d(k) = sum(betweencluster)/(k-1);

    %disp('S : ');
    %disp(s);
    %disp('D :');
    %disp(d);
    disp(k);
    a(k)=s(k)/d(k);
end

disp('Dot product');

plot(s);
saveas(gcf,'dot_s.jpg');
plot(d);
saveas(gcf,'dot_d.jpg');
plot(a);
saveas(gcf,'dot_a.jpg');
