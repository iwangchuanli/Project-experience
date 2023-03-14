package SY_7;

public class ProgramerTest {
public static void main(String[] args) {	
	Project project=new Project();
	project.setProgramer(new JavaProgramer());
	project.showProgramer();
	project.setProgramer(new NetProgramer());
	project.showProgramer();
	project.setProgramer(new RubyProgramer());
	project.showProgramer();
	project.setProgramer(new C_Programer());
	project.showProgramer();
}
}
