public class BankException extends Exception {
   String message;
   public BankException(int m,int n) {
       message="�����ʽ�"+m+"�Ǹ�����֧��"+n+"��������������ϵͳҪ��.";
   }
   public String warnMess() {
       return message;
   }
} 
