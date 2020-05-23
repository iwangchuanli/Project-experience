package SY_4;

public class CD {
	   int size;
	   int content[];
	   public void setSize(int size) {
	      this.size = size;
	      content = new int[size];
	   }
	   public int getSize() {
	      return size;
	   }
	   public int [] getContent() {
	      return content;
	   }
	   public void setContent(int [] b) {
	      int min=Math.min(content.length,b.length);  
	      for(int i=0;i<min;i++)//< DIV> 
	        content[i] = b[i];
	   }
	}
