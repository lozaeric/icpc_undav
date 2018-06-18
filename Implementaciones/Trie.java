class Trie {
	Node root = new Node();
	
	// Trie
	// agregar O(n) , buscar O(m)
	// genera un diccionario y busca eficientemente si existe una palabra o si es prefijo
	
	void add(char word[]) {
		Node cur = root;
		for (char c : word) {
			if (cur.go[c-'a'] == null)
				cur.go[c-'a'] = new Node();
			cur = cur.go[c-'a'];
		}
		cur.term = true;
	}
	
	boolean find(char word[]) {
		Node cur = root;
		for (char c : word) {
			if (cur.go[c-'a'] == null)
				return false;
			cur = cur.go[c-'a'];
		}
		return cur.term;
	}
	
	boolean isPrefix(char word[]) {
		Node cur = root;
		for (char c : word) {
			if (cur.go[c-'a'] == null)
				return false;
			cur = cur.go[c-'a'];
		}
		return true;
	}
}

class Node {
	Node go[] = new Node[26];
	boolean term = false;
}