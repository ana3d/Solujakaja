function IsEmpty() {
	if (document.forms['add'].summa.value === "") {
		alert("Tarkista summa!");
		return false;
	} else if (document.forms['add'].tapahtuma.value === "") {
		alert("Tarkista laskun nimi!");
	}
	return true;
}