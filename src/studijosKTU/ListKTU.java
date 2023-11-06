/**
 * @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 23
 * Koreguota: Aleksas Riškus, MIK, 2016
 * Tai pirmoji duomenų struktūros klasė, kuri leidžia apjungti
 *   atskirus objektus į vieną visumą - sąrašą.
 * Objektai, kurie bus dedami į sąrašą, turi įdiegti interfeisą Comparable<E>.
 *
 * Užduotis: 1. Peržiūrėkite ir išsiaiškinkite pateiktus metodus. 
 *			 2. Papildykite klasę naujais metodais, kuriuos aprašėte interfeise ListADT.
 * ****************************************************************************
 */
package studijosKTU;

import java.util.*;

/**
 * Pagrindinių sąrašo operacijų klasė.
 *
 * @param <E> Sąrašo elementų tipas (klasė)
 */
public class ListKTU<E extends Comparable<E>>
		implements ListADT<E>, Iterable<E>, Cloneable {

	private Node<E> first;   // rodyklė į pirmą mazgą
	private Node<E> last;    // rodyklė į paskutinį mazgą
	private Node<E> current; // rodyklė į einamąjį mazgą, naudojama metode getNext
	private int size;        // sąrašo dydis, tuo pačiu elementų skaičius

	/**
	 * Metodas add įdeda elementą į sąrašo pabaigą
	 *
	 * @param e - tai įdedamas elementas (jis negali būti null)
	 * @return true, jei operacija atlikta sėkmingai
	 */
	@Override
	public boolean add(E e) {
		if (e == null) {
			return false;   // nuliniai objektai nepriimami
		}
		if (first == null) {
			first = new Node<>(e, first);
			last = first;
		} else {
			Node<E> e1 = new Node(e, null);
			last.next = e1;
			last = e1;
		}
		size++;
		return true;
	}
	/**
	 *
	 * @return sąrašo dydis (elementų kiekis)
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Patikrina ar sąrašas yra tuščias
	 *
	 * @return true, jei tuščias
	 */
	@Override
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Išvalo sąrašą
	 */
	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
		current = null;
	}

	/**
	 * Grąžina elementą pagal jo indeksą (pradinis indeksas 0)
	 *
	 * @param k indeksas
	 * @return k-ojo elemento reikšmę; jei k yra blogas, gąžina null
	 */
	@Override
	public E get(int k) {
		if (k < 0 || k >= size) {
			return null;
		}
		current = first.findNode(k);
		return current.element;
	}

	/**
	 * Pereina prie kitos reikšmės ir ją grąžina
	 *
	 * @return kita reikšmė
	 */
	@Override
	public E getNext() {
		if (current == null) {
			return null;
		}
		current = current.next;
		if (current == null) {
			return null;
		}
		return current.element;
	}

	/**
	 * Sukuria sąrašo kopiją.
	 * @return sąrašo kopiją
	 */
	@Override
	public ListKTU<E> clone() {
		ListKTU<E> cl = null;
		try {
			cl = (ListKTU<E>) super.clone();
		} catch (CloneNotSupportedException e) {
			Ks.ern("Blogai veikia ListKTU klasės protėvio metodas clone()");
			System.exit(1);
		}
		if (first == null) {
			return cl;
		}
		cl.first = new Node<>(this.first.element, null);
		Node<E> e2 = cl.first;
		for (Node<E> e1 = first.next; e1 != null; e1 = e1.next) {
			e2.next = new Node<>(e1.element, null);
			e2 = e2.next;
		}
		cl.last = e2;
		cl.size = this.size;
		return cl;
	}
	
	/**
	 * Formuojamas Object masyvas (E tipo masyvo negalima sukurti), 
	 *   kur surašomi sąrašo elementai
	 *
	 * @return sąrašo elementų masyvas
	 */
	@Override
	public Object[] toArray() {
		Object[] a = new Object[size];
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			a[i++] = e1.element;
		}
		return a;
	}

	/**
	 * Tikrina ar objektas yra sarase
	 * @param O objektas
	 * @return logine reiksme
	 */
	@Override
	public boolean Contains(Object O) {
        return indexOf(O) > 0;
    }

	/**
	 * Tikrina ar objektas yra lygus listo objektui
	 * @param O objektas
	 * @return logine reiksme
	 */
	@Override
	public boolean Equals(Object O) {
        return O == this;
    }

	/**
	 * Metodas kuris pasalina is saraso norima objekta
	 * @param O objektas
	 * @return
	 */
	@Override
	public boolean Remove(Object O) {
		for(int i=0;i<size; i++){
			if(O.equals(get(i))){
				for(int j=i;j<size-1;j++) {
					if(current.next!=null)
						current.element = current.next.element;
					if(j+1==size){
						current.next = null;
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Nustato objekto indexa sarase
	 * @param O objektas
	 * @return objekto indexa
	 */
	@Override
	public int indexOf(Object O) {
		if (O == null) {
			for (int i = 0; i < size; i++) {
				if (get(i) == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (O.equals(get(i))) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Masyvo rikiavimas Arrays klasės metodu sort
	 */
	public void sortJava() {
		Object[] a = this.toArray();
		Arrays.sort(a);
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			e1.element = (E) a[i++];
		}
	}

	/**
	 * Rikiavimas Arrays klasės metodu sort pagal komparatorių
	 *
	 * @param c komparatorius
	 */
	public void sortJava(Comparator<E> c) {
		Object[] a = this.toArray();
		Arrays.sort(a, (Comparator) c);
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			e1.element = (E) a[i++];
		}
	}

	/**
	 * Sąrašo rikiavimas burbuliuko metodu
	 */
	public void sortBuble() {
		if (first == null) {
			return;
		}
		for (;;) {
			boolean jauGerai = true;
			Node<E> e1 = first;
			for (Node<E> e2 = first.next; e2 != null; e2 = e2.next) {
				if (e1.element.compareTo(e2.element) > 0) {
					E t = e1.element;
					e1.element = e2.element;
					e2.element = t;
					jauGerai = false;
				}
				e1 = e2;
			}
			if (jauGerai) {
				return;
			}
		}
	}

	/**
	 * Sąrašo rikiavimas burbuliuko metodu pagal komparatorių
	 *
	 * @param c komparatorius
	 */
	public void sortBuble(Comparator<E> c) {
		if (first == null) {
			return;
		}
		for (;;) {
			boolean jauGerai = true;
			Node<E> e1 = first;
			for (Node<E> e2 = first.next; e2 != null; e2 = e2.next) {
				if (c.compare(e1.element, e2.element) > 0) {
					E t = e1.element;
					e1.element = e2.element;
					e2.element = t;
					jauGerai = false;
				}
				e1 = e2;
			}
			if (jauGerai) {
				return;
			}
		}
	}

	/**
	 * Rikiavimo Metodas
	 */
	public void sortInsertion() {
		if (first == null) {
			return;
		}

		Node<E> sorted = null;

		Node<E> current = first;

		while (current != null) {
			Node<E> next = current.next;
			if (sorted == null || sorted.element.compareTo(current.element) >= 0) {
				current.next = sorted;
				sorted = current;
			} else {
				Node<E> search = sorted;
				while (search.next != null && search.next.element.compareTo(current.element) < 0) {
					search = search.next;
				}
				current.next = search.next;
				search.next = current;
			}
			current = next;
		}

		first = sorted;
	}

	/**
	 * Rikiavimo metodas su komparatoriumi
	 * @param c komparatorius
	 */
	public void sortInsertion(Comparator<E> c) {
		if (first == null) {
			return;
		}

		Node<E> sorted = null;

		Node<E> current = first;

		while (current != null) {
			Node<E> next = current.next;
			if (sorted == null || c.compare(sorted.element, current.element) >= 0) {
				current.next = sorted;
				sorted = current;
			} else {
				Node<E> search = sorted;
				while (search.next != null && c.compare(search.next.element,current.element) < 0) {
					search = search.next;
				}
				current.next = search.next;
				search.next = current;
			}
			current = next;
		}

		first = sorted;
	}
	/**
	 * Sukuria iteratoriaus objektą sąrašo elementų peržiūrai
	 *
	 * @return iteratoriaus objektą
	 */
	@Override
	public Iterator<E> iterator() {
		return new ListIteratorKTU();
	}

	/**
	 * Iteratoriaus metodų klasė
	 */
	private class ListIteratorKTU implements Iterator<E> {

		private Node<E> iterPosition;

		ListIteratorKTU() {
			iterPosition = first;
		}

		@Override
		public boolean hasNext() {
			return iterPosition != null;
		}

		@Override
		public E next() {
			E d = iterPosition.element;
			iterPosition = iterPosition.next;
			return d;
		}
	}

	/**
	 * Metodas iterpti nauja elemeta i norima vieta ir perstumpti kitus i desine
	 * @param index vietos indexas
	 * @param elem elementas
	 * @return
	 */
	public E set(int index, E elem){
		get(index-1);
		Node temp = new Node(elem,current.next);
		current.next=temp;
		return null;
	}

	/**
	 * Vidinė mazgo klasė
	 *
	 * @param <E> mazgo duomenų tipas
	 */
	private static class Node<E> {

		private E element;    // ji nematoma už klasės ListKTU ribų
		private Node<E> next; // next - kaip įprasta - nuoroda į kitą mazgą


		Node(E e, Node<E> next) { //mazgo konstruktorius
			this.element = e;
			this.next = next;
		}

		/**
		 * Suranda sąraše k-ąjį mazgą
		 *
		 * @param k ieškomo mazgo indeksas (prasideda nuo 0)
		 * @return surastas mazgas
		 */
		public Node<E> findNode(int k) {
			Node<E> e = this;
			for (int i = 0; i < k; i++) {
				e = e.next;
			}
			return e;
		}
	} // klasės Node pabaiga
}
