package fr.jdrape.adevent.adevent2017.enumeration;

public enum EnumOperation {

	INC("inc"), DEC("dec");

	private String operateur;

	private EnumOperation(String operateur) {
		this.operateur = operateur;
	}

	public String getOperateur() {
		return operateur;
	}

	public static EnumOperation getEnumOperation(String operateur) {
		for (EnumOperation enumOpe : EnumOperation.values()) {
			if (enumOpe.getOperateur().equals(operateur)) {
				return enumOpe;
			}
		}
		return null;
	}

}
