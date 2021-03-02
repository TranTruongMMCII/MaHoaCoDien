#include <iostream>
#include <string>

using namespace std;

int main(void){
	//khai bao bien luu tru ket qua
	string ketqua = "";
	//khai bao bien luu tru ten nguoi dung nhap vao
	string ten = "";
	//khai bao bien luu tru khoa nguoi dung nhap vao
	string khoa = "";
	//khai bao gia tri bien tam
	int value = 0;
	
	cout<<"Nhap ho ten: ";
	getline(cin, ten);
	
	cout<<"Nhap khoa: ";
	getline(cin, khoa);
	
	for(int i = 0; i < ten.length(); ++i){
		value = ((ten[i] - 'a') + (khoa[i%khoa.length()] - 'a')) % 26;
		ketqua += ('a' + value);
	}
	
	cout<<"Ket qua sau khi ma hoa "<<ten<<" : "<<ketqua;
}
