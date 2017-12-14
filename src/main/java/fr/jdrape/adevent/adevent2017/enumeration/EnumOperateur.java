package fr.jdrape.adevent.adevent2017.enumeration;

public enum EnumOperateur {

	EGAL("=="), DIFF("!="), SUP(">"), SUP_EGAL(">="), INF("<"), INF_EGAL("<=");

	private String operateur;

	private EnumOperateur(String operateur) {
		this.operateur = operateur;
	}

	public String getOperateur() {
		return operateur;
	}

	public static EnumOperateur getEnumOperateur(String operateur) {
		for (EnumOperateur enumOpe : EnumOperateur.values()) {
			if (enumOpe.getOperateur().equals(operateur)) {
				return enumOpe;
			}
		}
		return null;
	}

}
