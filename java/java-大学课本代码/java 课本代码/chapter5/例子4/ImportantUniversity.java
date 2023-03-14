public class ImportantUniversity extends University{
    void enterRule(double math,double english,double chinese) {
        double total=math+english+chinese;
        if(total>=220)  
           System.out.println("考分"+total+"达到重点大学录取线");
        else
           System.out.println("考分"+total+"未达到重点大学录取线");
    }
}