
X=csvread('C:\Users\Devanshu\OneDrive\Data Mining\HW3\onlydata.csv');
a=zeros(99,1);
s=zeros(99,1);
d=zeros(99,1);

for k = 2:100
    [idx,C,sumd,D] = kmeans(X,k,'Options',statset('UseParallel',1),'MaxIter',1000,'Replicates',10);
    incluster = zeros(k,1);
    betweencluster = zeros(k,1);
    count = zeros(k,1);


    %Incluster similarity
    for j = 1:176
        i = idx(j);
        for m = 1:176
            if (j~=m)
                temp = pdist2(X(j),X(m));
                incluster(i)=incluster(i)+temp;
                count(i)=count(i)+1;
            end
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
            if (j~=m)
                temp = pdist2(C(j),C(m));
                betweencluster(j)=betweencluster(j)+temp;
            end
        end
    end

    for i = 1:k
        incluster(i)=incluster(i)/(k-1);
        %fprintf('%16.f',incluster(i));
        %sdisp(incluster(i));
    end

    d(k) = sum(betweencluster)/(k-1);

    %disp('S : ');
    disp(s(k));
    %disp('D :');
    disp(d(k));

    a(k)=s(k)/d(k);
end

disp('Eucledian');
plot(s);
saveas(gcf,'eucledian_s.jpg');
plot(d);
saveas(gcf,'eucledian_d.jpg');
plot(a);
saveas(gcf,'eucledian_a.jpg');
