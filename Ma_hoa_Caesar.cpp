#include <bits/stdc++.h>

using namespace std;

int main(void){
	//bang chu cai tieng anh
	string text = "abcdefghijklmnopqrstuvwyz";
	//khai bao bien luu tru ket qua
	string ketqua = "";
	//khai bao bien luu tru ten nguoi dung nhap vao
	string ten = "";
	//khai bao bien luu tru khoa nguoi dung nhap vao
	int khoa = -1;
	//khai bao gia tri bien tam
	int value = 0;
	
	cout<<"Nhap ho ten: ";
	getline(cin, ten);
	
	cout<<"Nhap khoa: ";
	cin>>khoa;
	
	for(auto i : ten){
		//xu ly ma hoa Caeser Cipher
		value = ((i - 'a') + khoa) % 26;
		ketqua += ('a' + value);
	}
	
	cout<<"Ket qua sau khi ma hoa "<<ten<<" : "<<ketqua;
}
