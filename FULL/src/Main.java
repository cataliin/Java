void main() throws Exception {
    List<Factura> listaFacturi = ManagerDate.getFacturiFromTXT("src/facturi.txt");
    List<Factura> listaFacturi2 = ManagerDate.getFacturiFromBinar("src/fisierBinar.bin");
    List<Factura> listaFacturi3 = ManagerDate.getFacturiFromJSON("src/fisier.json");
    List<Factura> listaFacturi4 = ManagerDate.getFacturiFromXML("src/fisier.xml");
    List<Factura> listaFacturi5 = ManagerDate.getFacturiFromDB();
    System.out.println(listaFacturi5);
}