Caso 1: Quando se pretende se fala em actualizaço simultania, por exemplo contas bancarias partilhadas
--select... for update
--ou seja ninguem le este registo enquanto durar a edição

```
	
	this.manager.find(Conta.class, id, LockModeType.PESSIMISTIC_WRITE);

```

//----------------------------------------------
caso 2: enquanto se edita alguem apaga o registo
-- select ... lock in share mode
-- ou seja pode ler mas não editar ou apagar

O problema passa a estar em quem executará o delete do record

```
		
	this.manager.find(Category.class, id, LockModeType.PESSIMISTIC_READ);

```






