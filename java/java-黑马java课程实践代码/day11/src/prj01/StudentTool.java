package prj01;

public class StudentTool {

	public void listStudents(Student[] arr){
	//������ӡѧ����Ϣ
		for (int i = 0; i < arr.length; i++) {
			System.out.println("������"+arr[i].getName()+'\t'+"���䣺"+arr[i].getAge()+'\t'+"�ɼ���"+arr[i].getScore());
		}
	}
	public int getMaxScore(Student[] arr){
	//��ȡѧ���ɼ�����߷�
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			
			if (max < arr[i].getScore()) {
				max = arr[i].getScore();
			}
		}
		return max;
	}
	public Student getMaxStudent(Student[] arr){
		//��ȡ�ɼ���ߵ�ѧԱ
		int index = 0;
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			max = arr[i].getScore();
			if (max < arr[i].getScore()) {
				max = arr[i].getScore();
				index = i;
			}
		}
		return arr[index];
	}
	public int getAverageScore(Student[] arr){
	//��ȡѧ���ɼ���ƽ��ֵ
		int sum = 0;
		int avg = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i].getScore();
		}
		avg = sum / arr.length;
		return avg;
	}
	public int getCount(Student[] arr){
	//��ȡ�������ѧԱ����
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].getScore() < 60) {
				count ++;
			}
		}
		return count;
	}
	
}
