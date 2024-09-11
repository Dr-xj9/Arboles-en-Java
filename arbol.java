import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

class Nodo {
	private String atributo;
	private Map<String, Nodo> map;
	private String etiqueta;
	
	public Nodo(String atributo) {
		this.atributo = atributo;
		this.map = new HashMap<>();
		this.etiqueta= null;
	}
	public void agregar(String atributo, Nodo nodo) {
		map.put(atributo, nodo);
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String classify(Map<String, String> instancia) {
		if (etiqueta != null) {
			return etiqueta;
		}
		String valor = instancia.get(atributo);
		Nodo ob = map.get(valor);
		if (ob == null) {
			return null;
		}
		return ob.classify(instancia);
	}
}

public class arbol {
	Nodo root;
	public arbol(){
		root = construirArbol();
	}	
	public void Menu(){
		Scanner scanner = new Scanner(System.in);
		Map<String, String> map = new HashMap<>();
		System.out.println(" Respuesta si[1] no[2]:");
		System.out.print("¿Tienes fiebre? ");
		String respuesta = scanner.nextLine();
		map.put("fiebre", respuesta);
		System.out.print("¿Tienes tos? ");
		respuesta = scanner.nextLine();
		map.put("tos", respuesta);
		System.out.print("¿Tienes dolor de garganta? ");
		respuesta = scanner.nextLine();
		map.put("dolor_garganta", respuesta);
		System.out.print("¿Tienes dificultad para respirar? ");
		respuesta = scanner.nextLine();
		map.put("dificultad_respirar", respuesta);
		System.out.print("¿Tienes fatiga? ");
		respuesta = scanner.nextLine();
		map.put("fatiga", respuesta);
		String resultado = root.classify(map);
		System.out.println("El resultado es: " + resultado);
	}
	public static void main(String[] args) {
		arbol ob=new arbol();
		ob.Menu();
	}
	private static Nodo construirArbol() {
		Nodo root = new Nodo("fiebre");
		Nodo nodo1 = new Nodo("tos");
		Nodo nodo2 = new Nodo("fatiga");
		Nodo nodo3 = new Nodo("dolor_garganta");
		Nodo nodo4 = new Nodo("dificultad_respirar");
		Nodo hojaNodo1 = new Nodo(null);
		hojaNodo1.setEtiqueta("Resfriado");
		Nodo hojaNodo2 = new Nodo(null);
		hojaNodo2.setEtiqueta("Gripe");
		Nodo hojaNodo3 = new Nodo(null);
		hojaNodo3.setEtiqueta("COVID-19");
		root.agregar("1", nodo1);
		root.agregar("2", hojaNodo2);
		nodo1.agregar("1", hojaNodo1);
		nodo1.agregar("2", nodo2);
		nodo2.agregar("1", nodo3);
		nodo2.agregar("2", hojaNodo3);
		nodo3.agregar("1", hojaNodo3);
		nodo3.agregar("2", nodo4);
		nodo4.agregar("1", hojaNodo3);
		nodo4.agregar("2", hojaNodo2);
		return root;
	}
}