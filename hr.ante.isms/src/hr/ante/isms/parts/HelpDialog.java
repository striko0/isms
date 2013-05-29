package hr.ante.isms.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.mihalis.opal.opalDialog.Dialog;
import org.mihalis.opal.opalDialog.Dialog.OpalDialogType;
import org.eclipse.swt.custom.StyledText;

import de.kupzog.ktable.KTableSortedModel;

public class HelpDialog {

	 @Inject
	  MDirtyable dirty;

	 StyledText styledText;

	 public HelpDialog(Composite parent, int type) {
//	 @PostConstruct
//		public void createComposite(final Composite parent) {
//		 int type=1


				final StringBuilder stringBuilder = new StringBuilder();
				String title="";
				switch (type){
				case 1:
					title="Povjerljivost";

					stringBuilder.append("<b>1-Vrlo niska (javno)</b>\nU ovu se razinu ubrajaju otvorene informacije i svi drugi informacijski resursi kojima mo�e pristupiti bilo tko unutar ili izvan organizacije.\nPrimjerice informacije na web stranici organizacije. Informacije nisu osjetljivog karaktera ni za unutarnju ni za vanjsku upotrebu.\n");

					stringBuilder.append("<b>2-Niska (ograni�eno)</b>\nOva razina uklju�uje sve informacijske resurse kojima mogu pristupiti svi djelatnici organizacije i partneri na projektima koji zahtijevaju pristup resursima.\nPrimjerice interni dopisi, programska dokumentacija, telefonski imenici, opisi standarda, politike i procedure kojima se mogu koristiti djelatnici i partneri organizacije.\nPristup neovla�tenih osoba informacijskim resursima ove razine ima vrlo mali �tetan utjecaj.\n");

					stringBuilder.append("<b>3-Srednja (povjerljivo)</b>\nOva razina uklju�uje manje osjetljive informacijske resurse iz poslovanja organizacije kojima mogu pristupiti svi djelatnici organizacije bez ograni�enja.\nPodaci ove razine mogu biti dostupni i vanjskim partnerima, ali tek uz prihva�anje izjave o �uvanju poslovne tajne.\nEventualna javna objava ovih podataka ne bi trebala imati kriti�ne posljedice, osim mogu�eg negativnog javnog publiciteta.\n");

					stringBuilder.append("<b>4-Visoka (tajno)</b>\nOva razina uklju�uje informacijske resurse iz redovitog poslovanja organizacije, namijenjene za kori�tenje prije svega unutar poslovnih procesa organizacije.\nPodaci ove razine mogu biti interno dostupni samo osobama koje sudjeluju u poslovnim procesima. Vanjski pristup nije dozvoljen.\nNeovla�ten pristup ovim podacima mo�e negativno utjecati na efikasnost rada organizacije, mo�e izazvati financijske �tete i mo�e uzrokovati zna�ajan pad povjerenja klijenata.\nResursi ove razine uklju�uju financijske transakcije, poslovne planove pojedinih odjela,\ninformacije o djelatnicima i njihovim pla�ama, bud�etne i kadrovske planove i organizacijske sheme,\nvisoko povjerljive informacije zaprimljene od tre�e strane a koje se temeljem zakona, drugih propisa ili ugovora, klasificiraju na ovaj na�in.\nPristup do ovih podataka se u pravilu dodjeljuje pojedina�no, a ne grupi korisnika.\nPosljedice neovla�tenog pristupa mogu biti vrlo ozbiljne.\n");

					stringBuilder.append("<b>5-Vrlo visoka (vrlo tajno)</b>\nU ovu razinu pripadaju najosjetljiviji podaci iz poslovanja organizacije. Neovla�teni pristup, bilo unutar ili izvan organizacije potpuno je neprihvatljiv za organizaciju.\nBroj osoba s mogu�no��u pristupa podacima je ograni�en i po mogu�nosti �to manji.\nOvlasti za pristup mogu biti dodijeljene samo na osnovu vrlo strogih pravila. Resursi ove razine uklju�uju klju�eve za kriptiranje teksta, �ifre za pristup sefovima, itd.");
					break;
				case 2:
					title="Cjelovitost";

					stringBuilder.append("<b>1-Vrlo niska</b>\nInformacijski resurs �ija cjelovitost nema nikakav utjecaj na sigurnost informacijskog sustava organizacije.\n");

					stringBuilder.append("<b>2-Niska</b>\nSvi informacijski resursi �ija cjelovitost nije vrlo va�na, te se mo�e dobiti iz drugih izvora ili se mo�e jednostavno ponovno kreirati bez tro�kova u ljudskom radu i u financijama.\nGubitak cjelovitosti ima mali utjecaj na informacijsku sigurnost organizacije.\n");

					stringBuilder.append("<b>3-Srednja</b>\nInformacijski resursi �ija cjelovitost je va�na i potrebno ju je odr�avati.\nNe mo�e se ignorirati slu�ajna ili namjerna promjena resursa, ispravke su obavezne u kratkom roku, postupak obnove ima svoju cijenu i u ljudskom radu i u financijama.\nGubitak cjelovitosti ima vidljiv utjecaj na sigurnost informacijskog sustava organizacije i potrebno ga je za�tititi.\nOva razina uklju�uje statisti�ka izvje��a, rezultate programske obrade, izvorni programski kod i sli�no.\n");

					stringBuilder.append("<b>4-Visoka</b>\nInformacijski resurs �ija cjelovitost je vrlo va�na i koju treba odr�avati pod svaku cijenu.\nOva razina uklju�uje sve informacijske resurse koja je neophodna za obavljanje poslovnih procesa organizacije te ima veliku va�nost za organizaciju.\nGubitak cjelovitosti ima vrlo ozbiljan i negativan utjecaj na sigurnost informacijskog sustava organizacije i potrebno ga je vrlo sna�no za�tititi.\n");

					stringBuilder.append("<b>5-Vrlo visoka</b>\nZbog utjecaja na druge resurs informacijskog sustava, ne bi smjelo do�i do slu�ajne ili namjerne promjene.\nAko preventivne mjere nisu bile dostatne bitno je promjenu odmah uo�iti i ispraviti, postupak obnove mo�e biti vrlo slo�en i veoma skup.\nOva razina uklju�uje npr. ovla��ivanje i utvr�ivanje informacija o transakcijama, ugovor sa tre�im stranama, izvr�ni kod programa,\nbaze transakcijskih podataka, tablice mre�nih usmjeravanja i sigurnosnih ure�aja.\nGubitak cjelovitosti ima vrlo ozbiljan i negativan utjecaj na sigurnost informacijskog sustava organizacije,\nmo�e uzrokovati prestanak rada poslovnih procesa organizacije i potrebno ga je vrlo sna�no za�tititi.");
					break;
				case 3:
					title="Raspolo�ivost";

					stringBuilder.append("<b>1-Nije va�na (do 72 sata)</b>\nOva razina se koristi za informacijske resurse �ija raspolo�ivost nije kriti�na, te je dovoljno da se do nje do�e u roku do 72 radna sata.\n");

					stringBuilder.append("<b>2-Nije vrlo va�na (do 48 sati)</b>\nOva razina se koristi za informacijske resurse �iji rok raspolo�ivosti je do 48 radnih sati.\nGubitak raspolo�ivosti ima mali utjecaj na sigurnost informacijskog sustava.\n");

					stringBuilder.append("<b>3-Va�na (do 8 sati)</b>\nOva razina se koristi za informacijske resurse �iji rok raspolo�ivosti je do 8 radnih sati.\nGubitak raspolo�ivosti ima zna�ajan utjecaj na sigurnost informacijskog sustava.\n");

					stringBuilder.append("<b>4-Vrlo va�na ( do 1 sat)</b>\nGubitak informacija bi lo�e utjecao na korisnike i na poslovanje, a to bi rezultiralo gubitkom prihoda, resursa ili daljnjih prilika za organizaciju,\nizazvalo bi pojavu nesukladnosti organizacije sa zakonskim ili regulatornim zahtjevima ili bi donio organizaciji negativan publicitet.\nMaksimalna dozvoljena neupotrebljivost imovine je jedan sat dnevno.\n");

					stringBuilder.append("<b>5-Ekstremno va�na (kriti�na bez odgode)</b>\nGubitak informacija bi ozbiljno ugrozio pru�anje usluga korisnicima ili bi rezultirao nepovratnom �tetom u poslovanju, financijama ili samom ugledu organizacije.\nInformacijski resurs ove razine mora biti stalno na raspolaganju.");
					break;
				case 4:
					title="Poslovni Utjecaj";

					stringBuilder.append("<b>1-Nije va�na</b>\nResurs je dio poslovnih procesa organizacije ali nema bitnu ulogu u procesima.\n");

					stringBuilder.append("<b>2-Nije vrlo va�na</b>\nResurs ima bitnu ulogu u poslovnim procesima organizacije ili je dio zakonskih i/ili regulatornih obaveza.\n");

					stringBuilder.append("<b>3-Va�na</b>\nResurs ima va�nu ulogu u poslovnim procesima organizacije ili je dio zakonskih i/ili regulatornih obaveza.\n");

					stringBuilder.append("<b>4-Kriti�na<b>\nResurs ima kriti�nu ulogu u poslovnim procesima organizacije ili je dio zakonskih i/ili regulatornih obaveza.\n");

					stringBuilder.append("<b>5-Vrlo kriti�na</b>\nResurs ima vrlo kriti�nu ulogu u procesu ili je kriti�na za uskla�ivanje sa zakonskim i/ili regulatorskim propisima.");
					break;
				default:
					styledText.setText(null);
				break;
				}

				final Dialog dialog = new Dialog(true);
				dialog.getMessageArea().setVerticalScrollbar(true);
				dialog.setTitle(title);
				dialog.getShell().setLayout(new GridLayout());

				//dialog.getMessageArea().setHeight(350);
				dialog.getMessageArea().setText(stringBuilder.toString());
				dialog.setButtonType(OpalDialogType.OK);
				dialog.show();
			}

	@PreDestroy
	public void dispose() throws Exception {
	  System.out.println("Closing application");
	}

	 @Persist
	  public void save() {
	    System.out.println("Saving data");
	    // Save the data
	    // ...
	    // Now set the dirty flag to false
	    dirty.setDirty(false);
	  }


	@Focus
	public void setFocus() {
		styledText.setFocus();
	}
}
