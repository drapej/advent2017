package fr.jdrape.adevent.adevent2017.bean;

import java.util.HashMap;
import java.util.Map;

public class Noeud {

	private Integer poids;
	private Integer filsWeight;
	private String nom;
	private Map<String, Noeud> fils;
	private Noeud parent;

	public Noeud() {

	}

	public Noeud(Integer poids, String nom) {
		super();
		this.poids = poids;
		this.nom = nom;
	}

	public Integer getPoids() {
		return poids;
	}

	public void setPoids(Integer poids) {
		this.poids = poids;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Map<String, Noeud> getFils() {
		if (fils == null) {
			fils = new HashMap<>();
		}
		return fils;
	}

	public void addFils(Noeud noeudFils) {
		noeudFils.setParent(this);
		getFils().put(noeudFils.getNom(), noeudFils);
	}

	public Integer calculerPoidsTotalWeight() {
		Integer poids = this.poids;
		for (Noeud lFils : getFils().values()) {
			poids += lFils.calculerPoidsTotalWeight();
		}
		filsWeight = poids;
		return poids;

	}

	public Integer getFilsWeight() {
		return filsWeight;
	}

	public Noeud getParent() {
		return parent;
	}

	public void setParent(Noeud parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noeud other = (Noeud) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
