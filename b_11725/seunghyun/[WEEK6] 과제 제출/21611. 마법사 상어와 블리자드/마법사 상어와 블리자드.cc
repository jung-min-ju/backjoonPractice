#include <iostream>
#include <queue>
#include <vector>
#define MAXTABLE 49
#define MAXMAGIC 100
using namespace std;

vector<int> explosion(vector<int>arr, int ball[3]) { //���� �Լ�
	queue<int> q;
	vector<int> temp;
	int del[MAXTABLE], sum, num, exp;
	while (true) {
		sum = 0, num = 0, exp = 0;
		while (!temp.empty()) //temp���� ����
			temp.pop_back();
		q.push(arr[0]); //ù��° �� ť�� push
		for (int i = 1; i < arr.size(); i++) {
			if (arr[i] == q.front()) { //���� ���� ť�� �ִ� ���� �����ϸ� ��� push
				q.push(arr[i]);
			}
			else {
				if (q.size() >= 4) { //ť�� ����� ���� ������ 4�̻��̸� ����
					sum = q.size(); //ť ������ ����
					for (int i = 0; i < sum; i++) { //ť�� ����� �� ����
						ball[q.front() - 1]++; //ball�迭�� �ش� �� ����
						q.pop();
					}
					exp++;
				}
				while (!q.empty()) { //ť ���鼭 temp�� push_back
					temp.push_back(q.front());
					q.pop();
				}
				q.push(arr[i]);
			}
		}
		if (q.size() >= 4) { //ť ����
			sum = q.size();
			for (int i = 0; i < sum; i++) {
				ball[q.front()]++;
				q.pop();
			}
			exp++;
		}
		while (!q.empty()) {
			temp.push_back(q.front());
			q.pop();
		}
		arr = temp; //arr�� temp�� ��ü
		if (exp == 0)
			break;

	}
	return arr;
}

vector<int> realign(vector<int> arr, int size) {//������ �Լ�
	vector<int>temp;
	queue<int> q;
	q.push(arr[0]);
	for (int i = 1; i < arr.size(); i++) {
		if (q.front() == arr[i])//ť�� ����� ���� ���� �� �����ϸ� ��� push
			q.push(arr[i]);
		else {//�ٸ��� temp�� ť�� ����� �� ����, �� ������� push_back
			temp.push_back(q.size());
			temp.push_back(q.front());
			while (!q.empty())
				q.pop();
			q.push(arr[i]);
		}
		if (temp.size() == size)//���� ����� ���� ������ ������� ������ ����
			break;
	}
	if (temp.size() < size) {
		if (!q.empty()) { //ť ����
			temp.push_back(q.size());
			temp.push_back(q.front());
		}
	}
	return temp;
}

vector<int> magic(vector<int> arr, int d, int s) { //���� �Լ�
	int min, sum, num = 0, j = 0, del[MAXTABLE]; //������ ���� �迭
	vector<int> temp;
	switch (d) {
	case 1:
		sum = 0, min = 7; //sum=������ �ε���, min=sum�� ������ �ε���
		for (int i = 0; i < s; i++) {
			sum = sum + min; //������ �ε��� ���
			min += 8; //min�� 8���ϱ�
			del[num++] = sum - 1;
		}
		break;
	case 2:
		sum = 0, min = 3;
		for (int i = 0; i < s; i++) {
			sum = sum + min;
			min += 8;
			del[num++] = sum - 1;
		}
		break;
	case 3:
		sum = 0, min = 1;
		for (int i = 0; i < s; i++) {
			sum = sum + min;
			min += 8;
			del[num++] = sum - 1;
		}
		break;
	case 4:
		sum = 0, min = 5;
		for (int i = 0; i < s; i++) {
			sum = sum + min;
			min += 8;
			del[num++] = sum - 1;
		}
		break;
	}
	for (int i = 0; i < arr.size(); i++) {
		if (del[j] == i) { //������ �ε����� ������ continue�� �������� �ʰ� �ǳʶٱ�
			j++;
			continue;
		}
		temp.push_back(arr[i]);
	}
	return temp;
}

vector<int> set(int table[MAXTABLE][MAXTABLE], int n) { //���� ���ͷ� ���� �Լ�
	vector<int> arr;
	int a, b, c, i;
	a = n / 2; //�� �ε���
	b = n / 2 - 1; //�� �ε���
	c = 1; //�̵� Ƚ��
	if (table[a][b] != 0) //���� ���� 0�̸� �н�
		arr.push_back(table[a++][b]); //arr�� �ش� �� push
	else
		a++;
	while (1) {
		c++;
		for (int i = 0; i < c; i++) {
			if (table[a][b] == 0) { //�ش� �� 0�̸� push ���� �ʰ� continue
				b++;
				continue;
			}
			arr.push_back(table[a][b++]);
		}
		for (int i = 0; i < c; i++) {
			if (table[a][b] == 0) {
				a--;
				continue;
			}
			arr.push_back(table[a--][b]);
		}
		c++;
		for (int i = 0; i < c; i++) {
			if (table[a][b] == 0) {
				b--;
				continue;
			}
			arr.push_back(table[a][b--]);
		}
		if (a == 0 && b == -1)
			break;
		for (int i = 0; i < c; i++) {
			if (table[a][b] == 0) {
				a++;
				continue;
			}
			arr.push_back(table[a++][b]);
		}
	}
	return arr;
}

int main() {
	vector<int> arr;
	int ball[3] = { 0 }, size = 0; //ball=�� ������ ���ĵ� ���� ���� �迭, size=����� ��ġ�� ������ ������ ũ��

	int n, m, sum = 0;
	cin >> n >> m; //���� ũ��, ���� Ƚ�� �Է�
	size = n * n - 1; //��� ��ġ�� �����ϱ� ���� -1
	int table[MAXTABLE][MAXTABLE], magic_table[MAXMAGIC][2]; //���ڿ� ������ �����ϱ� ���� �迭
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> table[i][j];
		}
	}
	for (int i = 0; i < m; i++) {
		cin >> magic_table[i][0] >> magic_table[i][1];
	}

	arr = set(table, n); //���ڸ� arr ���ͷ� ����
	if (!arr.empty()) { //arr�� ��������� ������
		for (int i = 0; i < m; i++) { 
			arr = magic(arr, magic_table[i][0], magic_table[i][1]); 
			arr = explosion(arr, ball);
			if (arr.empty()) //���� �� arr�� ��������� �״�� ����
				break;
			else
				arr = realign(arr, size);
		}
	}

	sum += ball[0] * 1 + ball[1] * 2 + ball[2] * 3; //���� ���
	cout << sum;
}