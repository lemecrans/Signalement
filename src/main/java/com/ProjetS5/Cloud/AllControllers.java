package com.ProjetS5.Cloud;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import classmapping.*;

@RestController
public class AllControllers {

	@CrossOrigin(origins = "*")
	@RequestMapping("/listeSignalement")
	public List <Signalement> listeSignalement(){
		Signalement signalement = new Signalement();
		List<Signalement> liste = signalement.allSignalementEtat();
		return liste;
	}

    @CrossOrigin(origins = "*")
	@RequestMapping("/listeRegion")
	public List <Region> listeRegion(){
		Region region = new Region();
		List<Region> liste = region.allRegion();
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/listeStat")
	public List<Probleme> listeStat(){
		Probleme probleme = new Probleme();
		List<Probleme> liste = probleme.calculPourcentage();
		return liste;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/listeProbleme")
	public List<Probleme> listeProbleme(){
		Probleme probleme = new Probleme();
		List<Probleme> liste = probleme.allProbleme();
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listeRechercheRegion", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<String> listeRechercheRegion(@RequestPart("motAChercher") String motAChercher){
		Region region = new Region();
		List<String> liste = region.rechercheRegion(motAChercher);
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listeRechercheProbleme", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<Probleme> listeRechercheProbleme(@RequestPart("motAChercher") String motAChercher){
		Probleme probleme = new Probleme();
		List<Probleme> liste = probleme.rechercheProbleme(motAChercher);
		return liste;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/insertRegion", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public void insertRegion(@RequestPart("designationRegion")String designationRegion, @RequestPart("coordonneX")String coordonneX, @RequestPart("coordonneY")String coordonneY, @RequestPart("coordonneX1")String coordonneX1, @RequestPart("coordonneY1")String coordonneY1){
		Region region = new Region();
		double cx = Double.parseDouble(coordonneX);
		double cy = Double.parseDouble(coordonneY);
		double cx1 = Double.parseDouble(coordonneX1);
		double cy1 = Double.parseDouble(coordonneY1); 
		region.insertRegion(designationRegion, cx, cy, cx1, cy1);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listeliste", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<ProblemeRegion> listeliste(@RequestPart("id")int id){
		ProblemeRegion problemeRegion = new ProblemeRegion();
        List<ProblemeRegion> liste = problemeRegion.getPourcentageParRegion(id);
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/statByStatut", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<Signalement> statByStatut(@RequestPart("id")int id){
		Signalement signalement = new Signalement();
        List<Signalement> liste = signalement.signalementByEtat(id);
		return liste;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listeRecherchePro", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<Probleme> listeRecherchePro(@RequestPart("motAChercher") String motAChercher){
		String blem="";
		String region="";
		String statut="";
		if(motAChercher.equalsIgnoreCase("")==false) {
			if(motAChercher.split("!").length>1) {
				if(motAChercher.split("=")[0].equalsIgnoreCase("")==false) {
					blem=motAChercher.split("=")[0];
					System.out.println(blem);
				}
			}
			if(motAChercher.split("=").length>1) {
				if(motAChercher.split("=")[1].equalsIgnoreCase("")==false) {
					region=motAChercher.split("=")[1];
					if(region.split("!")[0].equalsIgnoreCase("")==false) {
						region=region.split("!")[0];
						System.out.println(region);
					}
				}
			}
			if(motAChercher.split("!").length>1) {
				if(motAChercher.split("!")[1].equalsIgnoreCase("")==false) {
					statut=motAChercher.split("!")[1];
					System.out.println(statut);
				}
			}
		}
		Probleme probleme = new Probleme();
		List<Probleme> liste = probleme.recherchePro(blem,region,statut);
		for(int i=0; i< liste.size();i++){
			System.out.println("ty le izy : "+liste.get(i).getDesignationProbleme());
		}
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listeRechercheProblemeParRegion", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<Region> listeRechercheProblemeParRegion(@RequestPart("motAChercher") String motAChercher){
		Region region = new Region();
		List<Region> liste = region.rechercheProblemeParRegion(motAChercher);
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/listeAdmin")
	public List<Admin> listeAdmin(){
		Admin admin = new Admin();
		List<Admin> liste = admin.allAdmin();
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/deleteSignalement", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String deleteSignalement(@RequestPart("idSignalement") int idSignalement){
		Signalement signalement = new Signalement();
		signalement.deleteSignalement(idSignalement);
		return "succes";
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/deleteRegion", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String deleteRegion(@RequestPart("id")String  id){
		Region.delete(id);
		return "succes";
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateSignalement", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public void updateSignalement(@RequestPart("idSignalement") int idSignalement, @RequestPart("idStatut")int idStatut){
		Signalement signalement = new Signalement();
		signalement.updateSignalement(idSignalement, idStatut);
		System.out.println("idSignalemet : "+idSignalement);
		System.out.println("idStatut : "+idStatut);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/listeStatut")
	public List<Statut> listeStatut(){
		Statut statut = new Statut();
		List<Statut> liste = statut.allStatut();
		return liste;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/listeUtilisateur")
	public List<Utilisateur> listeUtilisateur(){
		Utilisateur utilisateur = new Utilisateur();
		List<Utilisateur> liste = utilisateur.allUtilisateur();
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/lista")
	public List<Probleme> lista(){
		Probleme probleme = new Probleme();
		List<Probleme> liste = probleme.allBleme();
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/valideConnex" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public int valideConnex(@RequestPart("loginAdmin") String loginAdmin, @RequestPart("mdpAdmin") String mdpAdmin){
		Admin admin = new Admin();
		int retour = admin.validConnex(loginAdmin, mdpAdmin);
		return retour;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/statByStatut/mande" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<Signalement> statByStatut(@RequestPart("designationRegion") String designationRegion){
		Signalement stat = new Signalement();
		List<Signalement> list = stat.pourcentageByStat(designationRegion);
		System.out.println(list.size());
		return list;
	}

	// http://localhost:9000/seConnecertUtilisateur/Manda/manda
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/connexionUtilisateur" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String connexion(@RequestPart("login") String login, @RequestPart("password") String password){
		Utilisateur utilisateur = new Utilisateur();
		String valiny = "";
		String retour = "";
		try {
			valiny = utilisateur.seConnecter(login, password);
		} catch (Exception e) {
			e.getMessage();
		}
		if(valiny != ""){
			retour = "ERROR";
		}
		return retour;
	}


	// http://localhost:9000/updateUtilisateur/2/1
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateUtilisateur" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String update(@RequestPart("idRegion") int idRegion, @RequestPart("idUtilisateur") int idUtilisateur){
		Utilisateur utilisateur = new Utilisateur();
		int valiny = 0;
		String retour = "";
		try {
			valiny = utilisateur.updateUti(idRegion, idUtilisateur);
		} catch (Exception e) {
			e.getMessage();
		}
		if(valiny == 1){
			retour = "SUCCESS";
		}
		else {
			retour = "ERROR";
		}
		return retour;
	}

	// http://localhost:9000/deleteUtilisateur/23
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/deleteUtilisateur" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String deleteUtilisateur(@RequestPart("idUtilisateur") int idUtilisateur,@RequestPart("token") String token){
		Utilisateur utilisateur = new Utilisateur();
		int valiny = 0;
		String retour = "";
		try {
			valiny = utilisateur.deleteUti(idUtilisateur);
		} catch (Exception e) {
			e.getMessage();
		}
		if(valiny == 1){
			retour = "SUCCESS";
		}
		else {
			retour = "ERROR";
		}
		return retour;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/affectationParRegion" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String affectationParRegion(@RequestPart("idProbleme") int idProbleme,@RequestPart("idRegion") int idRegion){
		ProblemeRegion problemeRegion = new ProblemeRegion();
		String valiny = problemeRegion.affecterSignalement(idProbleme,idRegion);
		return valiny;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/delete/deleteUtilisateur" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String delete(@RequestPart("idUtilisateur") int idUtilisateur,@RequestPart("token") String token){
		Utilisateur utilisateur = new Utilisateur();
		int valiny = 0;
		String retour = "";
		if(Utilisateur.testToken(token, idUtilisateur)==true) {
			try {
				valiny = utilisateur.deleteUti(idUtilisateur);
			} catch (Exception e) {
				e.getMessage();
			}
		}
		if(valiny == 1){
			retour = "SUCCESS";
		}
		else {
			retour = "ERROR";
		}
		return retour;
	}

	// http://localhost:9000/vasy/mySignalement/2/sign1Admin
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/vasy/mySignalement" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<Signalement> mySignalement(@RequestPart("idUtilisateur") int idUtilisateur,@RequestPart("token") String token){
		Signalement signalement = new Signalement();
		List<Signalement> list = new ArrayList();
		if(Utilisateur.testToken(token, idUtilisateur)==true) {
			list = signalement.signalementByIdUtilisateur(idUtilisateur);
		}
		return list;
	}
	
	// http://localhost:9000/regionChef/chefregion
	 @CrossOrigin(origins = "*")
	 @RequestMapping("/regionChef/chefregion")
	 public List<ChefRegion> listeChefRegion(){
		 ChefRegion regionChef = new ChefRegion();
		 List<ChefRegion> liste = regionChef.listeChef();
		 return liste;
	 }
	 
	// http://localhost:9000/regionChef/chefregion
		 @CrossOrigin(origins = "*")
		 @RequestMapping(value ="/regionChef/infoChefRegion/{id}",method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
		 public ChefRegion infoChefRegion(@RequestPart("id")int id){
			 ChefRegion regionChef = new ChefRegion();
			 ChefRegion liste = regionChef.infoChefRegionById(id);
			 return liste;
		 }
		 
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/insertSignalement/signaler" , method = RequestMethod.POST ,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String insertSignalement(@RequestPart("file") MultipartFile file,@RequestPart("idUtilisateur") int idUtilisateur,@RequestPart("coordonneX") double coordonneX,@RequestPart("coordonneY") double coordonneY,@RequestPart("descriptionProbleme") String descriptionProbleme,@RequestPart("idProbleme") int idProbleme, @RequestPart("token") String token)throws IOException{
		Signalement signalement = new Signalement();
		int valiny = 0;
		String retour = "";
		try {
			if(Utilisateur.testToken(token, idUtilisateur)==true) {
				valiny = signalement.insertSignalement(idUtilisateur,coordonneX,coordonneY,descriptionProbleme,idProbleme,file.getOriginalFilename());
				String FILE_DIRECTORY="./";
				File myFile = new File(FILE_DIRECTORY+file.getOriginalFilename());
				myFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(myFile);
				fos.write(file.getBytes());
				fos.close();
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		if(valiny == 1){
			retour = "SUCCESS";
		}
		else {
			retour = "ERROR";
		}
		return retour;
	}
}