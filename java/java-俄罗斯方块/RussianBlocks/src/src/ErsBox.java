/**
 * File: ErsBox.java
 * User: Administrator
 * Date: Jan 15, 2003
 * Describe: ����˹����� Java ʵ��
 */

import java.awt.*;

/**
 * �����࣬����ɿ�Ļ���Ԫ�أ����Լ�����ɫ����ʾ������
 */
class ErsBox implements Cloneable {
	private boolean isColor;
	private Dimension size = new Dimension();

	/**
	 * ������Ĺ��캯��
	 * @param isColor �ǲ�����ǰ��ɫ��Ϊ�˷�����ɫ��
	 *      trueǰ��ɫ��false�ñ���ɫ
	 */
	public ErsBox(boolean isColor) {
		this.isColor = isColor;
	}

	/**
	 * �˷����ǲ�����ǰ��ɫ����
	 * @return boolean,true��ǰ��ɫ���֣�false�ñ���ɫ����
	 */
	public boolean isColorBox() {
		return isColor;
	}

	/**
	 * ���÷������ɫ��
	 * @param isColor boolean,true��ǰ��ɫ���֣�false�ñ���ɫ����
	 */
	public void setColor(boolean isColor) {
		this.isColor = isColor;
	}

	/**
	 * �õ��˷���ĳߴ�
	 * @return Dimension,����ĳߴ�
	 */
	public Dimension getSize() {
		return size;
	}

	/**
	 * ���÷���ĳߴ�
	 * @param size Dimension,����ĳߴ�
	 */
	public void setSize(Dimension size) {
		this.size = size;
	}

	/**
	 * ����Object��Object clone()��ʵ�ֿ�¡
	 * @return Object,��¡�Ľ��
	 */
	public Object clone() {
		Object cloned = null;
		try {
			cloned = super.clone();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return cloned;
	}
}
