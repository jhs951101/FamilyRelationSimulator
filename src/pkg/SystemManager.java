package pkg;

import java.util.Scanner;

public class SystemManager {
	
	private final int maxNumOfData = 100;
	private int id_CHILDRE = 1;
	
	/*
	 	<Parents>
		�θ�id[key] �Ŷ�id �ź�id
		
		<Children>
		�ڳ�id[key] �ڳ�� �ڳ༺�� �θ�id
	*/
	
	private String[][] PARENTS;
	private String[][] CHILDRE;
	private int size_PARENTS;
	private int size_CHILDRE;
	
	public SystemManager() {
		PARENTS = new String[3][];
		CHILDRE = new String[4][];
		
		for(int i=0; i<PARENTS.length; i++)
			PARENTS[i] = new String[maxNumOfData];
		
		for(int i=0; i<CHILDRE.length; i++)
			CHILDRE[i] = new String[maxNumOfData];
	}
	
	public void MANAGE() {
		System.out.println("Welcome to the Family Relation Simulator!");
		initialize();
		
		String input = "";
		String myName = "";
		
		while(true) {
			System.out.println("1 : Print my family");
			System.out.println("-1 : Exit");
			System.out.print("Choose what you want to do: ");
			input = getInput();
			clearScreen();
			
			if(input.equals("1")) {
				System.out.println("1 : Print your parents");
				System.out.println("2 : Print your spouse");
				System.out.println("3 : Print your children");
				System.out.println("4 : Print your brothers and sisters");
				System.out.println("5 : Print your grandparents");
				System.out.println("6 : Print your grandchildren");
				System.out.println("7 : Print the Family Relation Certificate about you");
				System.out.println("-1 : Back to the main menu");
				System.out.print("Choose what you want to do: ");
				input = getInput();
				clearScreen();
				
				if(input.equals("1")) {
					myName = getMyName();
					
					System.out.println("Mother: " + findParents(myName, "mother"));
					System.out.println("Father: " + findParents(myName, "father"));
					System.out.println("");
				}
				else if(input.equals("2")) {
					myName = getMyName();
					
					System.out.println("Spouse: " + findSpouse(myName));
					System.out.println("");
				}
				else if(input.equals("3")) {
					myName = getMyName();
					String[] children = findChildren(myName);
					
					System.out.print("Children: ");
					
					if(children.length > 0) {
						System.out.print(children[0]);
					
						for(int i=1; i<children.length; i++)
							System.out.print(", " + children[i]);
						
						System.out.println("");
					}
					else {
						System.out.println("NONE");
					}
				}
				else if(input.equals("4")) {
					myName = getMyName();
					String[] brosSis = findChildren(findParents(myName, "father"));
					
					System.out.print("Brothers and Sisters: ");
					
					if(brosSis.length > 0) {
						System.out.print(brosSis[0]);
					
						for(int i=1; i<brosSis.length; i++)
							System.out.print(", " + brosSis[i]);
						
						System.out.println("");
					}
					else {
						System.out.println("NONE");
					}
				}
				else if(input.equals("5")) {
					myName = getMyName();
					String father = findParents(myName, "father");
					String mother = findParents(myName, "mother");
					
					System.out.println("grandmother for your father: " + findParents(father, "mother"));
					System.out.println("grandfather for your father: " + findParents(father, "father"));
					
					System.out.println("grandmother for your mother: " + findParents(mother, "mother"));
					System.out.println("grandfather for your mother: " + findParents(mother, "father"));
					System.out.println("");
				}
				else if(input.equals("6")) {
					myName = getMyName();
					String[] children1 = findChildren(myName);
					
					if(children1.length > 0) {
						String[] grandchildren = new String[maxNumOfData];
						int size_gc = 0;
						
						for(int i=0; i<children1.length; i++) {
							String[] children2 = findChildren(children1[i]);
							
							for(int j=0; j<children2.length; j++) {
								grandchildren[size_gc] = children2[j];
								size_gc++;
							}
						}
						
						System.out.print("GrandChildren: ");
						
						if(size_gc > 0) {
							System.out.print(grandchildren[0]);
						
							for(int i=1; i<size_gc; i++)
								System.out.print(", " + grandchildren[i]);
							
							System.out.println("");
						}
						else {
							System.out.println("NONE");
							System.out.println("");
						}
					}
					else {
						System.out.println("GrandChildren: NONE");
						System.out.println("");
					}
				}
				else if(input.equals("7")) {
					myName = getMyName();
					
					System.out.println("You: " + myName);
					System.out.println("Mother: " + findParents(myName, "mother"));
					System.out.println("Father: " + findParents(myName, "father"));
					System.out.println("Spouse: " + findSpouse(myName));
					System.out.print("Children: ");
					
					String[] children = findChildren(myName);
					
					if(children.length > 0) {
						System.out.print(children[0]);
					
						for(int i=1; i<children.length; i++)
							System.out.print(", " + children[i]);
						
						System.out.println("");
					}
					else {
						System.out.println("NONE");
					}
				}
				else if(input.equals("-1")) {
					continue;
				}
			}
			else if(input.equals("-1")){
				System.out.print("Are you sure to exit? (y/n): ");
				getInput();
				clearScreen();
				
				if(input.equals("y"))
					break;
			}
			
			clearScreen();
		}
		
		System.out.println("Thank you for executing this program!");
	}
	
	private void initialize() {
		size_PARENTS = 0;
		size_CHILDRE = 0;
		
		// �츮 ���� �� ģô
		insertCHILDRE("������", "M", "1");
		insertCHILDRE("�ɼ���", "F", "1");
		insertCHILDRE("�ɹΰ�", "F", "2");
		insertCHILDRE("����ȣ", "M", "4");
		insertCHILDRE("����ȣ", "M", "4");
		insertCHILDRE("���", "F", "4");
		insertCHILDRE("������", "M", "5");
		insertCHILDRE("�̵���", "M", "5");
		insertCHILDRE("���±�", "M", "6");
		insertCHILDRE("������", "M", "6");
		insertCHILDRE("����", "M", "7");
		insertCHILDRE("���ص���", "F", "7");
		insertCHILDRE("�ܻ���", "M", "8");
		insertCHILDRE("������", "F", "8");
		insertCHILDRE("�̹μ�", "F", "8");
		insertCHILDRE("������", "F", "8");
		
		insertCHILDRE("�ѽ±�", "F", "9");
		insertCHILDRE("ģ�Ҿƹ���", "M", "10");
		insertCHILDRE("������", "F", "11");
		insertCHILDRE("�̱�ǥ", "M", "12");
		
		insertCHILDRE("��������", "F", "13");
		insertCHILDRE("����", "M", "14");
		insertCHILDRE("�ܼ���", "F", "15");
		insertCHILDRE("�̸��", "M", "16");


		insertPARENTS("1", "����ȣ", "������");
		insertPARENTS("2", "����ȣ", "��������");
		insertPARENTS("3", "���", "����");
		insertPARENTS("4", "ģ�Ҿƹ���", "�ѽ±�");
		insertPARENTS("5", "�ܻ���", "�ܼ���");
		insertPARENTS("6", "NULL", "�̹μ�");
		insertPARENTS("7", "�̸��", "������");
		insertPARENTS("8", "�̱�ǥ", "������");
		
		/*for(int i=0; i<4; i++) {
			for(int j=0; j<size_CHILDRE; j++) {
				System.out.print(CHILDRE[i][j] + " ");
			}
			System.out.println("");
		}
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<size_PARENTS; j++) {
				System.out.print(PARENTS[i][j] + " ");
			}
			System.out.println("");
		}*/
	}
	
	private void clearScreen() {   
		System.out.print("\n\n\n\n\n\n\n\n\n\n");
	}
	
	private String getInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}
	
	private String getMyName() {
		System.out.print("Input your name: ");
		return getInput();
	}
	
	private String findParents(String name, String type) {
		String parent = "NONE";
		String pos1 = findMyID(name);
		
		if(!pos1.equals("-1")) {
			String pos2 = "-1";
			
			for(int i=0; i<size_CHILDRE; i++) {
				if(CHILDRE[0][i].equals(pos1)) {
					pos2 = CHILDRE[3][i];
					break;
				}
			}
			
			if(!pos2.equals("-1")) {
				String pos3 = "-1";
				
				if(type.equals("father")) {
					for(int i=0; i<size_PARENTS; i++) {
						if(PARENTS[0][i].equals(pos2)) {
							pos3 = PARENTS[1][i];
							break;
						}
					}
					
					if(!pos3.equals("-1")) {
						parent = findMyName(pos3);
					}
				}
				else if(type.equals("mother")) {
					for(int i=0; i<size_PARENTS; i++) {
						if(PARENTS[0][i].equals(pos2)) {
							pos3 = PARENTS[2][i];
							break;
						}
					}
					
					if(!pos3.equals("-1")) {
						parent = findMyName(pos3);
					}
				}
			}
		}
		
		return parent;
	}
	
	private String findSpouse(String name) {
		String spouse = "NONE";
		String mySex = "NONE";
		String pos1 = findMyID(name);
		
		if(!pos1.equals("-1")) {
			String pos2 = "-1";
			mySex = findMySex(name);
			
			if(mySex.equals("M")) {
				for(int i=0; i<size_PARENTS; i++) {
					if(PARENTS[1][i].equals(pos1)) {
						pos2 = PARENTS[2][i];
						break;
					}
				}
			}
			else if(mySex.equals("F")) {
				for(int i=0; i<size_PARENTS; i++) {
					if(PARENTS[2][i].equals(pos1)) {
						pos2 = PARENTS[1][i];
						break;
					}
				}
			}
			
			if(!pos2.equals("-1")) {
				spouse = findMyName(pos2);
			}
		}
		
		return spouse;
	}
	
	private String[] findChildren(String name) {
		String[] tempArr = new String[maxNumOfData];
		int size_tempArr = 0;
		String pos1 = findMyID(name);
		
		if(!pos1.equals("-1")) {
			String mySex = findMySex(name);
			String pos2 = "-1";
			
			if(mySex.equals("M")) {
				for(int i=0; i<size_PARENTS; i++) {
					if(PARENTS[1][i].equals(pos1)) {
						pos2 = PARENTS[0][i];
						break;
					}
				}
			}
			else if(mySex.equals("F")) {
				for(int i=0; i<size_PARENTS; i++) {
					if(PARENTS[2][i].equals(pos1)) {
						pos2 = PARENTS[0][i];
						break;
					}
				}
			}
			
			if(!pos2.equals("-1")) {
				for(int i=0; i<size_CHILDRE; i++) {
					if(CHILDRE[3][i].equals(pos2)) {
						tempArr[size_tempArr] = CHILDRE[1][i];
						size_tempArr++;
					}
				}
			}
		}
		
		String[] children = new String[size_tempArr];
		
		for(int i=0; i<size_tempArr; i++) {
			children[i] = tempArr[i];
		}
		
		return children;
	}
	
	private String findMyID(String name) {
		String id = "-1";
		
		for(int i=0; i<size_CHILDRE; i++) {
			if(CHILDRE[1][i].equals(name)) {
				id = CHILDRE[0][i];
				break;
			}
		}
		
		return id;
	}
	
	private String findMyName(String id) {
		String name = "NONE";
		
		for(int i=0; i<size_CHILDRE; i++) {
			if(CHILDRE[0][i].equals(id)) {
				name = CHILDRE[1][i];
				break;
			}
		}
		
		return name;
	}
	
	private String findMySex(String name) {
		String mySex = "NONE";
		String pos = findMyID(name);
		
		if(!pos.equals("-1")) {
			for(int i=0; i<size_CHILDRE; i++) {
				if(CHILDRE[0][i].equals(pos)) {
					mySex = CHILDRE[2][i];
					break;
				}
			}
		}
		
		return mySex;
	}
	
	private void insertPARENTS(String parentsID, String maleName, String femaleName) {
		PARENTS[0][size_PARENTS] = parentsID;
		PARENTS[1][size_PARENTS] = findMyID(maleName);
		PARENTS[2][size_PARENTS] = findMyID(femaleName);
		
		size_PARENTS++;
	}
	
	private void insertCHILDRE(String childName, String childSex, String parentsID) {
		CHILDRE[1][size_CHILDRE] = childName;
		CHILDRE[2][size_CHILDRE] = childSex;
		CHILDRE[3][size_CHILDRE] = parentsID;
		CHILDRE[0][size_CHILDRE] = Integer.toString(id_CHILDRE);
		
		id_CHILDRE++;
		size_CHILDRE++;
	}
	
	private void deleteSPOUSE(String parentsID) {
		int dataPosition = -1;
		
		for(int i=0; i<size_PARENTS; i++) {
			if(PARENTS[0][i].equals(parentsID)) {
				dataPosition = i;
				break;
			}
		}
		
		for(int i=dataPosition; i<size_PARENTS-1; i++) {
			PARENTS[0][i] = PARENTS[0][i+1];
			PARENTS[1][i] = PARENTS[1][i+1];
			PARENTS[2][i] = PARENTS[2][i+1];
		}
		
		size_PARENTS--;
	}
	
	private void deleteCHILDRE(String childID) {
		int dataPosition = -1;
		
		for(int i=0; i<size_CHILDRE; i++) {
			if(CHILDRE[0][i].equals(childID)) {
				dataPosition = i;
				break;
			}
		}
		
		for(int i=dataPosition; i<size_CHILDRE-1; i++) {
			CHILDRE[0][i] = CHILDRE[0][i+1];
			CHILDRE[1][i] = CHILDRE[1][i+1];
			CHILDRE[2][i] = CHILDRE[2][i+1];
			CHILDRE[3][i] = CHILDRE[3][i+1];
		}
		
		size_CHILDRE--;
	}
}
