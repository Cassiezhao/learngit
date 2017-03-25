import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Class fileTool ʵ��VB��FRM�ļ���JAVAԴ�����ת��
 * 
 * @author κ����
 * @version 20130729
 * @see java.lang.Class
 * @since JDK 1.6.0_31
 */
public class fileTool {
	/** ��ʼ���ļ����� */
	private File file;
	/** Ŀ���ļ���Դ�ļ���ָ�� */
	private RandomAccessFile fp1, fp2;
	/** Ŀ���ļ������� */
	private String strFileName;
	/** ����ѭ�� */
	private static boolean goon = true;
	/** ���ɵ�JAVAԴ������������ */
	private String className;
	/** �������ļ����ֵõ����� */
	private String[] strFile = new String[100];
	/** �����һ�������е�Frame�Ϳؼ� */
	private String[] strFirstArray = new String[2];
	/** ��һ���ؼ������� */
	private String firstControlName;
	/** ���ļ������ɵ�����ĸ��� */
	private int index;
	/** ComandButton��δ��ʼ���ı�� */
	private int CommandButton = 1;
	/** ListBox��δ��ʼ���ı�� */
	private int ListBox = 1;
	/** CheckBox��δ��ʼ���ı�� */
	private int CheckBox = 1;
	/** OptionButton��δ��ʼ���ı�� */
	private int OptionButton = 1;
	/** TextBox��δ��ʼ���ı�� */
	private int TextBox = 1;
	/** Label��δ��ʼ���ı�� */
	private int Label = 1;
	/** ComboBox��δ��ʼ���ı�� */
	private int ComboBox = 1;
	/** fontChange��δ��ʼ���ı�� */
	private int fontChange = 1;
	/** password��δ��ʼ���ı�� */
	private int Password = 1;
	/** ���ܲ������� */
	private String order;
	private int Jscrollpane = 1;

	/**
	 * ���еĹ��췽��
	 * 
	 * @param String
	 *            fileName1 Դ�ļ� <br>
	 *            String fileName2 Ŀ���ļ�
	 * @return ��
	 * @exception ��
	 */
	public fileTool(String fileName1, String fileName2, String order) {
		file = new File(fileName1);
		strFileName = fileName2;
		className = getClassName(fileName2);
		this.order = order;
	}

	/**
	 * ��Ŀ���ļ���Դ�ļ������ڶ�д
	 * 
	 * @param ��
	 * @return ��
	 * @exception ��
	 * @throws FileNotFoundException
	 */
	public void openFile() throws FileNotFoundException {
		fp1 = new RandomAccessFile(file, "rw");
		fp2 = new RandomAccessFile(strFileName, "rw");
		try {
			index = getFileArray();
			getFirstArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �ر�Ŀ���ļ���Դ�ļ�
	 * 
	 * @param ��
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void closeFile() throws IOException {
		fp1.close();
		fp2.close();
	}

	/**
	 * ���ļ������ݶ�����ת�����ַ���
	 * 
	 * @param ��
	 * @return String str �ļ�������
	 * @exception ��
	 * @throws IOException
	 */
	public String getStringFile() throws IOException {
		/** ��¼�ļ��е�ÿ�У� ��¼�ļ��е��������� */
		String getFile, str = "";

		goon = true;
		fp1.seek(0);

		while (goon) {
			getFile = fp1.readLine();
			if (getFile == null)
				goon = false;
			else
				str += getFile + "  ";
		}
		return str;
	}

	/**
	 * �õ��ļ��е�WITH��HEIGHT��Ӧ��ֵ
	 * 
	 * @param String
	 *            sizeName Ҫ���ҵ�ֵ������
	 * @return int sum ֵ�Ĵ�С
	 * @exception ��
	 * @throws IOException
	 */
	public int getSize(String sizeName) throws IOException {
		/** ��¼��ȡ��ÿ���ֽ� */
		int readFile, sum = 0;

		fp1.seek(getStringFile().indexOf(sizeName) + 20);

		goon = true;
		while (goon) {
			readFile = fp1.read();
			if (readFile >= '0' && readFile <= '9') {
				readFile = readFile - '0';
				sum = sum * 10 + readFile;
			} else
				goon = false;
		}

		return sum;
	}

	/**
	 * ���س����к�����д���ļ�
	 * 
	 * @param int
	 *            returnSum ��Ҫ�س��ĸ��� <br>
	 *            int metreSum ��Ҫ����ĸ���
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void returnToFile(int returnSum, int metreSum) throws IOException {
		for (int i = 0; i < returnSum; i++) {
			fp2.write(13);
			fp2.write(10);
		}
		for (int j = 0; j < metreSum; j++)
			fp2.write(9);
	}

	/**
	 * �õ����ɵ�JAVAԴ������������
	 * 
	 * @param String
	 *            fileName Ŀ���ļ�������
	 * @return String str[0] ȡ��Ŀ���ļ�������"."�������
	 * @exception ��
	 */
	public String getClassName(String fileName) {
		/** ��¼������� */
		String str[] = null;

		str = fileName.split("\\.");

		return str[0];
	}

	/**
	 * ���ļ���"END"�ָ����ֱ��������
	 * 
	 * @param ��
	 * @return int i ���ɵ�����ĸ���
	 * @exception ��
	 * @throws IOException
	 */
	public int getFileArray() throws IOException {
		/** ��¼��ȡ��ÿ����Ϣ */
		String str;
		/** �ļ�ָ��ÿ���ƶ���λ�� */
		int i;
		/** ����ѭ�� */
		boolean goon_1 = true;

		fp1.seek(0);
		for (i = 0; goon_1;) {
			str = fp1.readLine();
			if (str == null)
				goon_1 = false;
			else if (str.length() == 6)
				i++;
			else
				strFile[i] += str + "  ";
		}

		return i;
	}

	/**
	 * �õ���һ���ؼ�������
	 * 
	 * @param String
	 *            str ��Ҫ���ҵ������
	 * @return String strName ��һ���ռ������
	 * @exception ��
	 * @throws IOException
	 */
	public String getFirstControlName(String str) throws IOException {
		/** ��¼�ַ�����λ�� */
		int location, i;
		/** ��¼��ȡ������ */
		String strName = "";
		/** ��¼ÿ�ζ�ȡ���ַ� */
		char str_1;
		boolean goon_2 = true;

		location = str.indexOf("StartUpPosition");
		for (i = 0; goon_2; i++) {
			str_1 = str.charAt(location + 46 + i);
			if (str_1 == ' ')
				goon_2 = false;
			else
				strName += str_1;
		}

		return strName;
	}

	/**
	 * �õ���Ҫ���ҵĿؼ�������
	 * 
	 * @param String
	 *            str ���ҿؼ����ڵ������
	 * @return String str �ؼ�������
	 * @exception ��
	 */
	public String getControlName(String str) {
		/** ��¼�ַ���λ�� */
		int location, i;
		/** ��¼��ȡ������ */
		String strName = "";
		/** ��¼ÿ�ζ�ȡ���ַ� */
		char str_1;
		/** ����ѭ�� */
		boolean goon_2 = true;

		location = 16;
		for (i = 0; goon_2; i++) {
			str_1 = str.charAt(location + i);
			if (str_1 == ' ')
				goon_2 = false;
			else
				strName += str_1;
		}

		return strName;
	}

	/**
	 * ��ʼ��JAVA���еİ�
	 * 
	 * @param String
	 *            strArray ��һ�������
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void initFirstPackCode(String strArray) throws IOException {
		/** ��¼��һ���ؼ������� */
		firstControlName = getFirstControlName(strArray);

		if (order.equals("-menu")) {
			fp2.writeBytes("package autoMenuPackage;");
			returnToFile(2, 0);
			fp2.writeBytes("import javax.swing.JDesktopPane;");
			returnToFile(1, 0);
			fp2.writeBytes("import javax.swing.JInternalFrame;");
			returnToFile(1, 0);
		}
		fp2.writeBytes("import java.awt.Container;");
		returnToFile(1, 0);
		fp2.writeBytes("import java.awt.Color;");
		returnToFile(1, 0);
		fp2.writeBytes("import java.awt.Font;");
		returnToFile(2, 0);
		if (order.equals("-normal")) {
			fp2.writeBytes("import javax.swing.JFrame;");
			returnToFile(1, 0);
			fp2.writeBytes("import java.awt.Dimension;");
			returnToFile(1, 0);
			fp2.writeBytes("import java.awt.Toolkit;");
			returnToFile(1, 0);
		}
		if (firstControlName.equals("CommandButton")) {
			fp2.writeBytes("import javax.swing.JButton;");
			CommandButton = 0;
		} else if (firstControlName.equals("Label")) {
			fp2.writeBytes("import javax.swing.JLabel;");
			Label = 0;
		} else if (firstControlName.equals("ListBox")) {
			fp2.writeBytes("import javax.swing.JList;");
			returnToFile(1, 0);
			fp2.writeBytes("import javax.swing.DefaultListModel;");
			returnToFile(1, 0);
			fp2.writeBytes("import javax.swing.JScrollPane;");
			ListBox = 0;
			Jscrollpane = 0;
		} else if (firstControlName.equals("CheckBox")) {
			fp2.writeBytes("import javax.swing.JCheckBox;");
			CheckBox = 0;
		} else if (firstControlName.equals("OptionButton")) {
			fp2.writeBytes("import javax.swing.JRadioButton;");
			OptionButton = 0;
		} else if (firstControlName.equals("TextBox")) {
			if (strArray.indexOf("PasswordChar  ") != -1) {
				if (Password == 1) {
					fp2.writeBytes("import javax.swing.JPasswordField;");
					Password = 0;
				}
			} else if (strArray.indexOf("MultiLine    ") != -1) {
				fp2.writeBytes("import javax.swing.JTextArea;");
				if (Jscrollpane == 1) {
					returnToFile(1, 0);
					fp2.writeBytes("import javax.swing.JScrollPane;");
				}
			} else {
				if (TextBox == 1) {
					fp2.writeBytes("import javax.swing.JTextField;");
					TextBox = 0;
				}
			}
		} else if (firstControlName.equals("ComboBox")) {
			fp2.writeBytes("import javax.swing.JComboBox;");
			ComboBox = 0;
		}
		returnToFile(1, 0);
	}

	/**
	 * ��ʼ�����еİ�
	 * 
	 * @param String
	 *            strArray ���������
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void initPackCode(String strArray) throws IOException {
		/** ��¼�ؼ������� */
		String controlName = getControlName(strArray);

		if (controlName.equals("CommandButton")) {
			if (CommandButton == 1) {
				fp2.writeBytes("import javax.swing.JButton;");
				returnToFile(1, 0);
				CommandButton = 0;
			}
		} else if (controlName.equals("Label")) {
			if (Label == 1) {
				fp2.writeBytes("import javax.swing.JLabel;");
				returnToFile(1, 0);
				Label = 0;
			}
		} else if (controlName.equals("ListBox")) {
			if (ListBox == 1) {
				fp2.writeBytes("import javax.swing.JList;");
				returnToFile(1, 0);
				fp2.writeBytes("import javax.swing.DefaultListModel;");
				returnToFile(1, 0);
				fp2.writeBytes("import javax.swing.JScrollPane;");
				returnToFile(1, 0);
				ListBox = 0;
				Jscrollpane = 0;
			}
		} else if (controlName.equals("CheckBox")) {
			if (CheckBox == 1) {
				fp2.writeBytes("import javax.swing.JCheckBox;");
				returnToFile(1, 0);
				CheckBox = 0;
			}
		} else if (controlName.equals("OptionButton")) {
			if (OptionButton == 1) {
				fp2.writeBytes("import javax.swing.JRadioButton;");
				returnToFile(1, 0);
				OptionButton = 0;
			}
		} else if (controlName.equals("TextBox")) {
			if (!(strArray.indexOf("PasswordChar ") == -1)) {
				if (Password == 1) {
					fp2.writeBytes("import javax.swing.JPasswordField;");
					returnToFile(1, 0);
					Password = 0;
				}
			} else if (strArray.indexOf("MultiLine    ") != -1) {
				fp2.writeBytes("import javax.swing.JTextArea;");
				if (Jscrollpane == 1) {
					returnToFile(1, 0);
					fp2.writeBytes("import javax.swing.JScrollPane;");
				}
			} else {
				if (TextBox == 1) {
					fp2.writeBytes("import javax.swing.JTextField;");
					returnToFile(1, 0);
					TextBox = 0;
				}
			}
		} else if (controlName.equals("ComboBox")) {
			if (ComboBox == 1) {
				fp2.writeBytes("import javax.swing.JComboBox;");
				returnToFile(1, 0);
				ComboBox = 0;
			}
		}
	}

	/**
	 * ��ʼ��ͷ��һЩ���еô����
	 * 
	 * @param ��
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void initheadCode() throws IOException {
		fp2.writeBytes("/**");
		returnToFile(1, 0);
		fp2.writeBytes(" *Class <code>{Class name}</code>{Class function describe}");
		returnToFile(1, 0);
		fp2.writeBytes(" *");
		returnToFile(1, 0);
		fp2.writeBytes(" *@author ");
		returnToFile(1, 0);
		fp2.writeBytes(" *@version ");
		returnToFile(1, 0);
		fp2.writeBytes(" *@see      java.lang.Class");
		returnToFile(1, 0);
		fp2.writeBytes(" *@since    JDK{jdk -version}");
		returnToFile(1, 0);
		fp2.writeBytes(" */");
		returnToFile(1, 0);
		fp2.writeBytes("public class " + className);
		returnToFile(1, 0);
		fp2.writeBytes("{");
		returnToFile(1, 1);
	}

	/**
	 * �õ�Frame������
	 * 
	 * @param ��
	 * @return String str_2 Frame������
	 * @exception ��
	 */
	public String getFrameName() {
		/** ���ƶ�ȡ�ַ���λ�� */
		int i;
		/** ����ѭ�� */
		boolean goon_3 = true;
		/** ��¼ÿ�ն�ȡ���ַ� */
		char str_1;
		/** ��¼��ȡ������ */
		String str_2 = "";

		for (i = 0; goon_3; i++) {
			str_1 = strFile[0].charAt(32 + i);
			if (str_1 == ' ')
				goon_3 = false;
			else
				str_2 += str_1;
		}

		return str_2;
	}

	/**
	 * �õ���һ�����еı���������
	 * 
	 * @param ��
	 * @return String strName ����������
	 * @exception ��
	 * @throws IOException
	 */
	public String getHeadVaribaleName() throws IOException {
		/** ��¼�ַ������� */
		int location = 0, location_1, i;
		/** ��¼��ȡ������ */
		String strName = "";
		/** ��¼ÿ�ζ�ȡ���ַ� */
		char str_1;
		/** ����ѭ�� */
		boolean goon_2 = true;
		/** ��¼��һ���ؼ������� */
		String firstControlName = getFirstControlName(strFile[0]);

		location_1 = strFile[0].indexOf("StartUpPosition");

		if (firstControlName.equals("CommandButton"))
			location = 30;
		else if (firstControlName.equals("Label"))
			location = 22;
		else if (firstControlName.equals("ListBox"))
			location = 24;
		else if (firstControlName.equals("CheckBox"))
			location = 25;
		else if (firstControlName.equals("OptionButton"))
			location = 29;
		else if (firstControlName.equals("TextBox"))
			location = 24;
		else if (firstControlName.equals("ComboBox"))
			location = 25;

		for (i = 0; goon_2; i++) {
			str_1 = strFile[0].charAt(location_1 + 30 + location + i);
			if (str_1 == ' ')
				goon_2 = false;
			else
				strName += str_1;
		}

		return strName;
	}

	/**
	 * ��ʼ��ͷ�����еñ�������Ĵ���
	 * 
	 * @param ��
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void initHeadVariableCode() throws IOException {
		/** ��¼�����ڵ����� */
		String frameName = getFrameName();
		/** ��¼��һ���ؼ������� */
		String firstControlName = getFirstControlName(strFile[0]);

		if (order.equals("-normal")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JFrame " + frameName + ";");
			returnToFile(1, 1);
		} else if (order.equals("-menu")) {
			fp2.writeBytes("/** define JInternalFrame to make a small window*/");
			returnToFile(1, 1);
			fp2.writeBytes("private JInternalFrame " + frameName + ";");
			returnToFile(1, 1);
			fp2.writeBytes("/** to quote mainwindow's JDesktopPane*/");
			returnToFile(1, 1);
			fp2.writeBytes("private JDesktopPane contain;");
			returnToFile(1, 1);
		}
		fp2.writeBytes("/** */");
		returnToFile(1, 1);
		fp2.writeBytes("private Container con;");
		returnToFile(1, 1);

		if (firstControlName.equals("CommandButton")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JButton " + getHeadVaribaleName() + ";");
			returnToFile(1, 1);
		} else if (firstControlName.equals("Label")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JLabel " + getHeadVaribaleName() + ";");
			returnToFile(1, 1);
		} else if (firstControlName.equals("ListBox")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JList " + getHeadVaribaleName() + ";");
			returnToFile(1, 1);
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JScrollPane " + "jsp" + getHeadVaribaleName().substring(4) + ";");
			returnToFile(1, 1);
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private DefaultListModel " + "dlm" + getHeadVaribaleName().substring(4) + ";");
			returnToFile(1, 1);
		} else if (firstControlName.equals("CheckBox")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JCheckBox " + getHeadVaribaleName() + ";");
			returnToFile(1, 1);
		} else if (firstControlName.equals("OptionButton")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JRadioButton " + getHeadVaribaleName() + ";");
			returnToFile(1, 1);
		} else if (firstControlName.equals("TextBox")) {
			if (strFile[0].indexOf("PasswordChar  ") != -1) {
				fp2.writeBytes("/** */");
				returnToFile(1, 1);
				fp2.writeBytes("private JPasswordField " + getHeadVaribaleName() + ";");
				returnToFile(1, 1);
			} else if (strFile[0].indexOf("MultiLine   ") != -1) {
				fp2.writeBytes("/** */");
				returnToFile(1, 1);
				fp2.writeBytes("private JTextArea " + getHeadVaribaleName() + ";");
				returnToFile(1, 1);
				fp2.writeBytes("/** */");
				returnToFile(1, 1);
				fp2.writeBytes("private JScrollPane jsp" + getHeadVaribaleName().substring(4) + ";");
				returnToFile(1, 1);
			} else {
				fp2.writeBytes("/** */");
				returnToFile(1, 1);
				fp2.writeBytes("private JTextField " + getHeadVaribaleName() + ";");
				returnToFile(1, 1);
			}
		} else if (firstControlName.equals("ComboBox")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JComboBox " + getHeadVaribaleName() + ";");
			returnToFile(1, 1);
		}
	}

	/**
	 * �õ�����������
	 * 
	 * @param String
	 *            array ���ļ������ɵĸ������� <br>
	 *            String controlName VB�пؼ�������
	 * @return String name VB����Ӧ��JAVA�пؼ�������
	 * @exception ��
	 */
	public String getVariableName(String array, String controlName) {
		/** ��¼��ȡ������ */
		String name = "";
		/** ��¼λ�� */
		int location = 0, i;
		/** ��¼ÿ�ζ�ȡ���ַ� */
		char str_1;
		/** ����ѭ�� */
		boolean goon_4 = true;

		if (controlName.equals("CommandButton"))
			location = 30;
		else if (controlName.equals("Label"))
			location = 22;
		else if (controlName.equals("ListBox"))
			location = 24;
		else if (controlName.equals("CheckBox"))
			location = 25;
		else if (controlName.equals("OptionButton"))
			location = 29;
		else if (controlName.equals("TextBox"))
			location = 24;
		else if (controlName.equals("ComboBox"))
			location = 25;

		for (i = 0; goon_4; i++) {
			str_1 = array.charAt(location + i);
			if (str_1 == ' ')
				goon_4 = false;
			else
				name += str_1;
		}
		return name;
	}

	/**
	 * ��ʼ��JAVA�б�������Ĵ���
	 * 
	 * @param String
	 *            fileStringArray ���ļ������ɵĸ�������
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void initVariableCode(String fileStringArray) throws IOException {
		/** ��¼�ؼ������� */
		String controlName = getControlName(fileStringArray);

		if (controlName.equals("CommandButton")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JButton " + getVariableName(fileStringArray, "CommandButton") + ";");
			returnToFile(1, 1);
		} else if (controlName.equals("Label")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JLabel " + getVariableName(fileStringArray, "Label") + ";");
			returnToFile(1, 1);
		} else if (controlName.equals("ListBox")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JList " + getVariableName(fileStringArray, "ListBox") + ";");
			returnToFile(1, 1);
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes(
					"private JScrollPane " + "jsp" + getVariableName(fileStringArray, "ListBox").substring(4) + ";");
			returnToFile(1, 1);
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private DefaultListModel " + "dlm"
					+ getVariableName(fileStringArray, "ListBox").substring(4) + ";");
			returnToFile(1, 1);
		} else if (controlName.equals("CheckBox")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JCheckBox " + getVariableName(fileStringArray, "CheckBox") + ";");
			returnToFile(1, 1);
		} else if (controlName.equals("OptionButton")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JRadioButton " + getVariableName(fileStringArray, "OptionButton") + ";");
			returnToFile(1, 1);
		} else if (controlName.equals("TextBox")) {
			if (fileStringArray.indexOf("PasswordChar   ") != -1) {
				fp2.writeBytes("/** */");
				returnToFile(1, 1);
				fp2.writeBytes("private JPasswordField " + getVariableName(fileStringArray, "TextBox") + ";");
				returnToFile(1, 1);
			} else if (fileStringArray.indexOf("MultiLine   ") != -1) {
				fp2.writeBytes("/** */");
				returnToFile(1, 1);
				fp2.writeBytes("private JTextArea " + getHeadVaribaleName() + ";");
				returnToFile(1, 1);
				fp2.writeBytes("/** */");
				returnToFile(1, 1);
				fp2.writeBytes("private JScrollPane jsp" + getHeadVaribaleName().substring(4) + ";");
				returnToFile(1, 1);
			} else {
				fp2.writeBytes("/** */");
				returnToFile(1, 1);
				fp2.writeBytes("private JTextField " + getVariableName(fileStringArray, "TextBox") + ";");
				returnToFile(1, 1);
			}
		} else if (controlName.equals("ComboBox")) {
			fp2.writeBytes("/** */");
			returnToFile(1, 1);
			fp2.writeBytes("private JComboBox " + getVariableName(fileStringArray, "ComboBox") + ";");
			returnToFile(1, 1);
		}
	}

	/**
	 * ��ʼ��JAVA�м���е�һЩ����
	 * 
	 * @param ��
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void initBodyCode() throws IOException {
		fp2.writeBytes("/**method describe");
		returnToFile(1, 1);
		fp2.writeBytes(" *@param");
		returnToFile(1, 1);
		fp2.writeBytes(" *@return");
		returnToFile(1, 1);
		fp2.writeBytes(" *@exception");
		returnToFile(1, 1);
		fp2.writeBytes(" *@throws");
		returnToFile(1, 1);
		fp2.writeBytes(" */");
		returnToFile(1, 1);
		fp2.writeBytes("public " + className + "()");
		returnToFile(1, 1);
		fp2.writeBytes("{");
		if (order.equals("-menu")) {
			returnToFile(1, 2);
			fp2.writeBytes("setContain(InitMenu.getPane());");
		}
		returnToFile(1, 2);
		fp2.writeBytes("intiFrame();");
		returnToFile(1, 1);
		fp2.writeBytes("}");
		returnToFile(2, 1);
		if (order.equals("-normal")) {
			fp2.writeBytes("public Dimension getScreenSize()");
			returnToFile(1, 1);
			fp2.writeBytes("{");
			returnToFile(1, 2);
			fp2.writeBytes("return Toolkit.getDefaultToolkit().getScreenSize();");
			returnToFile(1, 1);
			fp2.writeBytes("}");
			returnToFile(2, 1);
		}
		fp2.writeBytes("/**method describe");
		returnToFile(1, 1);
		fp2.writeBytes(" *@param");
		returnToFile(1, 1);
		fp2.writeBytes(" *@return");
		returnToFile(1, 1);
		fp2.writeBytes(" *@exception");
		returnToFile(1, 1);
		fp2.writeBytes(" *@throws");
		returnToFile(1, 1);
		fp2.writeBytes(" */");
		returnToFile(1, 1);
		fp2.writeBytes("public void intiFrame()");
		returnToFile(1, 1);
		fp2.writeBytes("{");
		returnToFile(1, 2);

	}

	/**
	 * �õ���Ӧ�ֶε�����
	 * 
	 * @param String
	 *            strArray ��Ҫ���ҵ��ֶ����ڵ����� String filed Ҫ���ҵ��ֶε����� int
	 *            mark ��ǡ�"1"Ϊ������˫���ŵ��ֶΣ������Ĳ�����˫���ŵ��ֶ�
	 * @return String strProperty �ֶζ�Ӧ������
	 * @exception ��
	 * @throws IOException
	 */
	public String getProperty(String strArray, String filed, int mark) throws IOException {
		/** ��¼ÿ�ζ�ȡ�ַ� */
		char ch, end;
		/** ��¼��ȡ������ */
		String strProperty = "";
		/** ����ѭ�� */
		boolean goon_5 = true;
		/** ��¼λ�� */
		int i, index, location = 0;

		if (mark == 1) {
			location = 20;
			end = ' ';
		} else {
			location = 21;
			end = '"';
		}

		for (i = 0; goon_5; i++) {
			index = strArray.indexOf(filed);
			ch = strArray.charAt(index + location + i);
			if (ch == end)
				goon_5 = false;
			else
				strProperty += ch;
		}

		return strProperty;
	}

	/**
	 * ��һ�������е���������ؼ��ָ�
	 * 
	 * @param ��
	 * @return ��
	 * @exception ��
	 */
	public void getFirstArray() {
		/** ��¼λ�� */
		int location;

		location = strFile[0].indexOf("StartUpPosition");
		strFirstArray[0] = strFile[0].substring(0, location - 1);
		strFirstArray[1] = strFile[0].substring(location + 30);
	}

	/**
	 * �õ�Ĭ�ϵ�����
	 * 
	 * @param String
	 *            strFileArray ���ļ������ĵ�һ������
	 * @return String retrunFont �õ�����ĸ����ԣ���"\n"�ָ�
	 * @exception ��
	 * @throws IOException
	 */
	public String getDefaultFont(String strFileArray) throws IOException {
		/** �õ���������� */
		String name = getProperty(strFileArray, "Name            =", 0);
		/** ��¼����Ĵ�С */
		String size;// getProperty(strFileArray, "Size", 1);
		/** ��¼�����weight */
		String weight = getProperty(strFileArray, "Weight          =", 1);
		/** ��¼��������� */
		String italic = getProperty(strFileArray, "Italic          =", 1);
		/** ��¼�����������Ϣ */
		String font = null, returnFont;
		int size_1;
		String str[], size_2[];
		str = strFileArray.split("BeginProperty Font ");
		size = getProperty(str[1], "Size  ", 1);
		size_2 = size.split("\\.");
		size_1 = Integer.valueOf(size_2[0]) + 3;

		if (weight.equals("400") && italic.equals("0"))
			font = "Font.PLAIN";
		else if (weight.equals("700") && italic.equals("0"))
			font = "Font.BOLD";
		else if (weight.equals("400") && italic.equals("-1"))
			font = "Font.ITALIC";
		else if (weight.equals("700") && italic.equals("-1"))
			font = "Font.BOLD + Font.ITALIC";

		returnFont = name + "\n" + font + "\n" + size_1;
		return returnFont;
	}

	/**
	 * д�������ڵĳ�ʼ������
	 * 
	 * @param ��
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void initFrameCode() throws IOException {
		/** ��¼Ĭ������ */
		String defaultFont;
		/** ��¼����ĸ�����Ϣ */
		String font[];
		/** �õ������ڵı����� */
		String frameVariableName = getFrameName();
		/** �õ������ڵ����� */
		String frameWindowName = getProperty(strFile[0], "Caption  ", 0);
		/** �õ����ڵ�width */
		int width = getSize("ClientWidth") + getSize("ClientLeft") * 2;
		/** �õ����ڵ�heigth */
		int heigth = getSize("ClientHeight") + getSize("ClientLeft") + getSize("ClientTop");

		if (strFirstArray[0].indexOf("BeginProperty Font") == -1)
			;
		else {
			defaultFont = getDefaultFont(strFirstArray[0]);
			font = defaultFont.split("\n");
			fontChange = 0;
			fp2.writeBytes("Font normalFont = new Font(\"" + font[0] + "\", " + font[1] + ", (int) " + font[2] + ");");
			returnToFile(2, 2);
		}
		if (order.equals("-menu")) {
			fp2.writeBytes(frameVariableName + " = new JInternalFrame(\"" + frameWindowName
					+ "\", false, true, false, true);");
			returnToFile(1, 2);
		} else if (order.equals("-normal")) {
			fp2.writeBytes(frameVariableName + " = new JFrame(\"" + frameWindowName + "\");");
			returnToFile(1, 2);
		}
		if (order.equals("-normal")) {
			fp2.writeBytes(frameVariableName + ".setResizable(false);");
			returnToFile(1, 2);
		}
		fp2.writeBytes("con = " + frameVariableName + ".getContentPane();");
		returnToFile(1, 2);
		fp2.writeBytes("con.setLayout(null);");
		returnToFile(1, 2);
		fp2.writeBytes(frameVariableName + ".setSize(" + width / 15 + ", " + heigth / 15 + ");");
		returnToFile(1, 2);
		if (strFirstArray[0].indexOf("MaxButton") == -1)
			;
		else if (order.equals("-normal")) {
			fp2.writeBytes(frameVariableName + ".setResizable(false);");
			returnToFile(1, 2);
		}
		if (order.equals("-normal")) {
			fp2.writeBytes(frameVariableName + ".setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);");
			returnToFile(1, 2);
			fp2.writeBytes(frameVariableName + ".setLocation((getScreenSize().width - " + frameVariableName
					+ ".getWidth())/2, (getScreenSize().height - " + frameVariableName + ".getHeight())/2);");
			returnToFile(1, 2);
		}
		fp2.writeBytes(frameVariableName + ".setVisible(true);");
		returnToFile(1, 2);
		if (order.equals("-menu"))
			fp2.writeBytes("contain.add(" + frameVariableName + ");");
	}

	/**
	 * ��VB�пؼ������ֱ�Ϊ��Ӧ��JAVA�пռ������
	 * 
	 * @param String
	 *            controlName VB�пؼ�������
	 * @return String javaName JAVA�пؼ�������
	 * @exception ��
	 */
	public String changeControlName(String controlName) {
		/** ��¼JAVA�пؼ������� */
		String javaName = null;

		if (controlName.equals("CommandButton"))
			javaName = "JButton";
		else if (controlName.equals("Label"))
			javaName = "JLabel";
		else if (controlName.equals("ListBox"))
			javaName = "JList";
		else if (controlName.equals("CheckBox"))
			javaName = "JCheckBox";
		else if (controlName.equals("OptionButton"))
			javaName = "JRadioButton";
		else if (controlName.equals("TextBox"))
			javaName = "JTextField";
		else if (controlName.equals("ComboBox"))
			javaName = "JComboBox";

		return javaName;
	}

	/**
	 * �õ��ؼ�����ɫ����
	 * 
	 * @param String
	 *            strFileArray �ؼ��ֶ����ڵ�����
	 * @return String str ��ɫ��ֵ
	 * @exception ��
	 * @throws IOException
	 */
	public String getColor(String strFileArray) throws IOException {
		/** ��¼VB�е���ɫ��Ϣ�� ����VB�е���ɫ��Ϣ */
		String strColor_1, strColor_2, str;
		/** ����VB�е���ɫ��Ϣ */
		String[] strColor = new String[4];

		strColor_1 = getProperty(strFileArray, "ForeColor  ", 1);
		strColor_2 = strColor_1.substring(2, 10);
		strColor[0] = strColor_2.substring(0, 2);
		strColor[1] = strColor_2.substring(2, 4);
		strColor[2] = strColor_2.substring(4, 6);
		strColor[3] = strColor_2.substring(6, 8);
		str = strColor[0] + strColor[3] + strColor[2] + strColor[1];

		return str;
	}

	/**
	 * һ��ÿ���ؼ����еĳ�ʼ������
	 * 
	 * @param String
	 *            strFileArray �ؼ��ֶ����ڵ����� String controlVariable
	 *            �ؼ���Ӧ�ı�����
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void special(String strFileArray, String controlVariable) throws IOException {
		if (strFileArray.indexOf("BeginProperty Font") == -1) {
			if (fontChange == 1)
				;
			else {
				fp2.writeBytes(controlVariable + ".setFont(normalFont);");
				returnToFile(1, 2);
			}
		} else {
			String font[];
			font = getDefaultFont(strFileArray).split("\n");
			fp2.writeBytes(controlVariable + ".setFont(new Font(\"" + font[0] + "\", " + font[1] + ", (int)" + font[2]
					+ "));");
			returnToFile(1, 2);
		}
		if (strFileArray.indexOf("Enabled") == -1)
			;
		else {
			fp2.writeBytes(controlVariable + ".setEnabled(false);");
			returnToFile(1, 2);
		}
		if (strFileArray.indexOf("ForeColor") == -1)
			;
		else {
			String color;

			color = getColor(strFileArray);
			fp2.writeBytes(controlVariable + ".setForeground(new Color(0x" + color + "));");
			returnToFile(1, 2);
		}
	}

	/**
	 * д������ؼ���ʼ���Ĵ���
	 * 
	 * @param String
	 *            strFileArray �ؼ��ֶ����ڵ�����
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void initControlCode(String strFileArray) throws IOException {
		/** ��¼�ؼ���width, height, left, top */
		String width, height, left, top;
		/** ��¼�ؼ������֣� �ؼ��ı����� */
		String controlName, controlVariable;
		/** ��¼VB��caption��ֵ */
		String caption = null;
		controlName = getControlName(strFileArray);
		/** JAVA�пؼ������� */
		String controlNameInJava = changeControlName(controlName);
		controlVariable = getVariableName(strFileArray, controlName);
		width = getProperty(strFileArray, "Width           =", 1);
		height = getProperty(strFileArray, "Height          =", 1);
		left = getProperty(strFileArray, "Left            =", 1);
		top = getProperty(strFileArray, "Top             =", 1);

		if ((!controlName.equals("ListBox")) && (!controlName.equals("TextBox")) && (!controlName.equals("ComboBox"))
				&& (!controlName.equals("PictureBox")) && (!controlName.equals("Line"))) {
			if (strFileArray.indexOf("Caption         =") == -1)
				caption = "";
			else
				caption = getProperty(strFileArray, "Caption         =", 0);
			returnToFile(2, 2);
			fp2.writeBytes(controlVariable + " = new " + controlNameInJava + "(\"" + caption + "\");");
			returnToFile(1, 2);
			if ((controlName.equals("CheckBox") || controlName.equals("OptionButton"))
					&& strFileArray.indexOf("Value") != -1) {
				fp2.writeBytes(controlVariable + ".setSelected(true);");
				returnToFile(1, 2);
			}
			fp2.writeBytes(
					controlVariable + ".setBounds(" + Integer.valueOf(left) / 15 + ", " + Integer.valueOf(top) / 15
							+ ", " + Integer.valueOf(width) / 15 + ", " + Integer.valueOf(height) / 15 + ");");
			returnToFile(1, 2);
			special(strFileArray, controlVariable);
			fp2.writeBytes("con.add(" + controlVariable + ");");
		} else if (controlName.equals("TextBox")) {
			if (strFileArray.indexOf("PasswordChar  ") != -1) {
				returnToFile(2, 2);
				fp2.writeBytes(controlVariable + " = new JPasswordField();");
				returnToFile(1, 2);
				if (strFileArray.indexOf("Text        ") != -1) {
					fp2.writeBytes(
							controlVariable + ".setText(\"" + getProperty(strFileArray, "Text      ", 0) + "\");");
					returnToFile(1, 2);
				}
				fp2.writeBytes(
						controlVariable + ".setBounds(" + Integer.valueOf(left) / 15 + ", " + Integer.valueOf(top) / 15
								+ ", " + Integer.valueOf(width) / 15 + ", " + Integer.valueOf(height) / 15 + ");");
				returnToFile(1, 2);
				special(strFileArray, controlVariable);
				fp2.writeBytes("con.add(" + controlVariable + ");");
			} else if (strFileArray.indexOf("MultiLine    ") != -1) {
				returnToFile(1, 2);
				fp2.writeBytes(controlVariable + " = new JTextArea();");
				returnToFile(1, 2);
				fp2.writeBytes("jsp" + controlVariable.substring(4) + " = new JScrollPane(" + controlVariable + ");");
				returnToFile(1, 2);
				fp2.writeBytes("jsp" + controlVariable.substring(4) + ".setBounds(" + Integer.valueOf(left) / 15 + ", "
						+ Integer.valueOf(top) / 15 + ", " + Integer.valueOf(width) / 15 + ", "
						+ Integer.valueOf(height) / 15 + ");");
				returnToFile(1, 2);
				special(strFileArray, controlVariable);
				fp2.writeBytes("con.add(" + "jsp" + controlVariable.substring(4) + ");");
			} else {
				returnToFile(2, 2);
				fp2.writeBytes(controlVariable + " = new " + controlNameInJava + "();");
				returnToFile(1, 2);
				if (strFileArray.indexOf("Text        ") != -1) {
					fp2.writeBytes(
							controlVariable + ".setText(\"" + getProperty(strFileArray, "Text      ", 0) + "\");");
					returnToFile(1, 2);
				}
				fp2.writeBytes(
						controlVariable + ".setBounds(" + Integer.valueOf(left) / 15 + ", " + Integer.valueOf(top) / 15
								+ ", " + Integer.valueOf(width) / 15 + ", " + Integer.valueOf(height) / 15 + ");");
				returnToFile(1, 2);
				special(strFileArray, controlVariable);
				fp2.writeBytes("con.add(" + controlVariable + ");");
			}
		} else if (controlName.equals("ListBox")) {
			returnToFile(2, 2);
			fp2.writeBytes("dlm" + controlVariable.substring(4) + " = new DefaultListModel();");
			returnToFile(1, 2);
			fp2.writeBytes(
					controlVariable + " = new " + controlNameInJava + "(dlm" + controlVariable.substring(4) + ");");
			returnToFile(1, 2);
			fp2.writeBytes("jsp" + controlVariable.substring(4) + " = new JScrollPane(" + controlVariable + ");");
			returnToFile(1, 2);
			fp2.writeBytes("jsp" + controlVariable.substring(4) + ".setBounds(" + Integer.valueOf(left) / 15 + ", "
					+ Integer.valueOf(top) / 15 + ", " + Integer.valueOf(width) / 15 + ", "
					+ Integer.valueOf(height) / 15 + ");");
			returnToFile(1, 2);
			special(strFileArray, "jsp" + controlVariable.substring(4));
			fp2.writeBytes("con.add(" + "jsp" + controlVariable.substring(4) + ");");
		} else if (controlName.equals("ComboBox")) {
			returnToFile(2, 2);
			fp2.writeBytes(controlVariable + " = new " + controlNameInJava + "();");
			returnToFile(1, 2);
			fp2.writeBytes(
					controlVariable + ".setBounds(" + Integer.valueOf(left) / 15 + ", " + Integer.valueOf(top) / 15
							+ ", " + Integer.valueOf(width) / 15 + ", " + Integer.valueOf(height) / 15 + ");");
			returnToFile(1, 2);
			special(strFileArray, controlVariable);
			fp2.writeBytes("con.add(" + controlVariable + ");");
		}
	}

	private void initMenuCode() throws IOException {
		// TODO Auto-generated method stub
		fp2.writeBytes("/** quote mainwindow's JDesktopPane");
		returnToFile(1, 1);
		fp2.writeBytes(" *@param null");
		returnToFile(1, 1);
		fp2.writeBytes(" *@return null");
		returnToFile(1, 1);
		fp2.writeBytes(" *@exception null");
		returnToFile(1, 1);
		fp2.writeBytes(" *@throws null");
		returnToFile(1, 1);
		fp2.writeBytes(" */");
		returnToFile(1, 1);
		fp2.writeBytes("private void setContain(JDesktopPane contain)");
		returnToFile(1, 1);
		fp2.writeBytes("{");
		returnToFile(1, 2);
		fp2.writeBytes("this.contain = contain;");
		returnToFile(1, 1);
		fp2.writeBytes("}");
		returnToFile(1, 1);
	}

	/**
	 * д�����д���
	 * 
	 * @param ��
	 * @return ��
	 * @exception ��
	 * @throws IOException
	 */
	public void initCode() {
		int i;
		try {
			initFirstPackCode(strFile[0]);
			for (i = 1; i <= index - 1; i++)
				initPackCode(strFile[i]);
			returnToFile(1, 0);
			initheadCode();
			initHeadVariableCode();
			for (i = 1; i <= index - 1; i++)
				initVariableCode(strFile[i]);
			returnToFile(1, 1);
			if (order.equals("-menu")) {
				initMenuCode();
			}
			returnToFile(1, 1);
			initBodyCode();
			initFrameCode();
			initControlCode(strFirstArray[1]);
			for (i = 1; i <= index - 1; i++)
				initControlCode(strFile[i]);
			returnToFile(1, 1);
			fp2.writeBytes("}");
			returnToFile(2, 1);
			fp2.writeBytes("public static void main(String[] args)");
			returnToFile(1, 1);
			fp2.writeBytes("{");
			returnToFile(1, 2);
			fp2.writeBytes("new " + className + "();");
			returnToFile(1, 1);
			fp2.writeBytes("}");
			returnToFile(1, 0);
			fp2.writeBytes("}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String arg[]) {
		fileTool oa = new fileTool(arg[0], arg[1], arg[2]);

		try {
			oa.openFile();
			oa.initCode();
			oa.closeFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}