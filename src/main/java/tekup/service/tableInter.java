package tekup.service;


import tekup.module.table;

public interface tableInter {
	public table ajouter(table t ) ;
	public table chercher(table tableResto);
	table modifierTable(table tableResto);
	table supprimerTable(table tablere);
	table  plusReserve();


}
