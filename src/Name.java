
public class Name implements Comparable<Name>{

	private String name;
	private String  gender ;
	private int number ;



	public Name(String name,String gender,int number) {
		super();
		this.name = name;
		this.gender = gender;
		this.number = number;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
		return  name + "," + number + "," + gender ;
	}

	@Override
	public int compareTo(Name o) {

		return -1*(this.number - o.number);
	}







}
