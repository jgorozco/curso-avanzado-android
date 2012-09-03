import java.util.HashSet;


public class example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashSet<Object> objetos=new HashSet<Object>();
		objetos.add(new Integer("1"));
		objetos.add(new Integer("1"));
		objetos.add(new Object());
		objetos.add(new Object());
		System.out.println(String.valueOf(new Object().hashCode()));
		System.out.println(String.valueOf(new Object().hashCode()));
		System.out.println(String.valueOf(new Integer("1").hashCode()));
		System.out.println(String.valueOf(new Integer("1").hashCode()));
		System.out.println(String.valueOf(objetos.size()));
		System.gc();
	}

}
