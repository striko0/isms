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

					stringBuilder.append("<b>1-Vrlo niska (javno)</b>\nU ovu se razinu ubrajaju otvorene informacije i svi drugi informacijski resursi kojima može pristupiti bilo tko unutar ili izvan organizacije.\nPrimjerice informacije na web stranici organizacije. Informacije nisu osjetljivog karaktera ni za unutarnju ni za vanjsku upotrebu.\n");

					stringBuilder.append("<b>2-Niska (ogranièeno)</b>\nOva razina ukljuèuje sve informacijske resurse kojima mogu pristupiti svi djelatnici organizacije i partneri na projektima koji zahtijevaju pristup resursima.\nPrimjerice interni dopisi, programska dokumentacija, telefonski imenici, opisi standarda, politike i procedure kojima se mogu koristiti djelatnici i partneri organizacije.\nPristup neovlaštenih osoba informacijskim resursima ove razine ima vrlo mali štetan utjecaj.\n");

					stringBuilder.append("<b>3-Srednja (povjerljivo)</b>\nOva razina ukljuèuje manje osjetljive informacijske resurse iz poslovanja organizacije kojima mogu pristupiti svi djelatnici organizacije bez ogranièenja.\nPodaci ove razine mogu biti dostupni i vanjskim partnerima, ali tek uz prihvaæanje izjave o èuvanju poslovne tajne.\nEventualna javna objava ovih podataka ne bi trebala imati kritiène posljedice, osim moguæeg negativnog javnog publiciteta.\n");

					stringBuilder.append("<b>4-Visoka (tajno)</b>\nOva razina ukljuèuje informacijske resurse iz redovitog poslovanja organizacije, namijenjene za korištenje prije svega unutar poslovnih procesa organizacije.\nPodaci ove razine mogu biti interno dostupni samo osobama koje sudjeluju u poslovnim procesima. Vanjski pristup nije dozvoljen.\nNeovlašten pristup ovim podacima može negativno utjecati na efikasnost rada organizacije, može izazvati financijske štete i može uzrokovati znaèajan pad povjerenja klijenata.\nResursi ove razine ukljuèuju financijske transakcije, poslovne planove pojedinih odjela,\ninformacije o djelatnicima i njihovim plaæama, budžetne i kadrovske planove i organizacijske sheme,\nvisoko povjerljive informacije zaprimljene od treæe strane a koje se temeljem zakona, drugih propisa ili ugovora, klasificiraju na ovaj naèin.\nPristup do ovih podataka se u pravilu dodjeljuje pojedinaèno, a ne grupi korisnika.\nPosljedice neovlaštenog pristupa mogu biti vrlo ozbiljne.\n");

					stringBuilder.append("<b>5-Vrlo visoka (vrlo tajno)</b>\nU ovu razinu pripadaju najosjetljiviji podaci iz poslovanja organizacije. Neovlašteni pristup, bilo unutar ili izvan organizacije potpuno je neprihvatljiv za organizaciju.\nBroj osoba s moguænošæu pristupa podacima je ogranièen i po moguænosti što manji.\nOvlasti za pristup mogu biti dodijeljene samo na osnovu vrlo strogih pravila. Resursi ove razine ukljuèuju kljuèeve za kriptiranje teksta, šifre za pristup sefovima, itd.");
					break;
				case 2:
					title="Cjelovitost";

					stringBuilder.append("<b>1-Vrlo niska</b>\nInformacijski resurs èija cjelovitost nema nikakav utjecaj na sigurnost informacijskog sustava organizacije.\n");

					stringBuilder.append("<b>2-Niska</b>\nSvi informacijski resursi èija cjelovitost nije vrlo važna, te se može dobiti iz drugih izvora ili se može jednostavno ponovno kreirati bez troškova u ljudskom radu i u financijama.\nGubitak cjelovitosti ima mali utjecaj na informacijsku sigurnost organizacije.\n");

					stringBuilder.append("<b>3-Srednja</b>\nInformacijski resursi èija cjelovitost je važna i potrebno ju je održavati.\nNe može se ignorirati sluèajna ili namjerna promjena resursa, ispravke su obavezne u kratkom roku, postupak obnove ima svoju cijenu i u ljudskom radu i u financijama.\nGubitak cjelovitosti ima vidljiv utjecaj na sigurnost informacijskog sustava organizacije i potrebno ga je zaštititi.\nOva razina ukljuèuje statistièka izvješæa, rezultate programske obrade, izvorni programski kod i slièno.\n");

					stringBuilder.append("<b>4-Visoka</b>\nInformacijski resurs èija cjelovitost je vrlo važna i koju treba održavati pod svaku cijenu.\nOva razina ukljuèuje sve informacijske resurse koja je neophodna za obavljanje poslovnih procesa organizacije te ima veliku važnost za organizaciju.\nGubitak cjelovitosti ima vrlo ozbiljan i negativan utjecaj na sigurnost informacijskog sustava organizacije i potrebno ga je vrlo snažno zaštititi.\n");

					stringBuilder.append("<b>5-Vrlo visoka</b>\nZbog utjecaja na druge resurs informacijskog sustava, ne bi smjelo doæi do sluèajne ili namjerne promjene.\nAko preventivne mjere nisu bile dostatne bitno je promjenu odmah uoèiti i ispraviti, postupak obnove može biti vrlo složen i veoma skup.\nOva razina ukljuèuje npr. ovlašæivanje i utvrðivanje informacija o transakcijama, ugovor sa treæim stranama, izvršni kod programa,\nbaze transakcijskih podataka, tablice mrežnih usmjeravanja i sigurnosnih ureðaja.\nGubitak cjelovitosti ima vrlo ozbiljan i negativan utjecaj na sigurnost informacijskog sustava organizacije,\nmože uzrokovati prestanak rada poslovnih procesa organizacije i potrebno ga je vrlo snažno zaštititi.");
					break;
				case 3:
					title="Raspoloživost";

					stringBuilder.append("<b>1-Nije važna (do 72 sata)</b>\nOva razina se koristi za informacijske resurse èija raspoloživost nije kritièna, te je dovoljno da se do nje doðe u roku do 72 radna sata.\n");

					stringBuilder.append("<b>2-Nije vrlo važna (do 48 sati)</b>\nOva razina se koristi za informacijske resurse èiji rok raspoloživosti je do 48 radnih sati.\nGubitak raspoloživosti ima mali utjecaj na sigurnost informacijskog sustava.\n");

					stringBuilder.append("<b>3-Važna (do 8 sati)</b>\nOva razina se koristi za informacijske resurse èiji rok raspoloživosti je do 8 radnih sati.\nGubitak raspoloživosti ima znaèajan utjecaj na sigurnost informacijskog sustava.\n");

					stringBuilder.append("<b>4-Vrlo važna ( do 1 sat)</b>\nGubitak informacija bi loše utjecao na korisnike i na poslovanje, a to bi rezultiralo gubitkom prihoda, resursa ili daljnjih prilika za organizaciju,\nizazvalo bi pojavu nesukladnosti organizacije sa zakonskim ili regulatornim zahtjevima ili bi donio organizaciji negativan publicitet.\nMaksimalna dozvoljena neupotrebljivost imovine je jedan sat dnevno.\n");

					stringBuilder.append("<b>5-Ekstremno važna (kritièna bez odgode)</b>\nGubitak informacija bi ozbiljno ugrozio pružanje usluga korisnicima ili bi rezultirao nepovratnom štetom u poslovanju, financijama ili samom ugledu organizacije.\nInformacijski resurs ove razine mora biti stalno na raspolaganju.");
					break;
				case 4:
					title="Poslovni Utjecaj";

					stringBuilder.append("<b>1-Nije važna</b>\nResurs je dio poslovnih procesa organizacije ali nema bitnu ulogu u procesima.\n");

					stringBuilder.append("<b>2-Nije vrlo važna</b>\nResurs ima bitnu ulogu u poslovnim procesima organizacije ili je dio zakonskih i/ili regulatornih obaveza.\n");

					stringBuilder.append("<b>3-Važna</b>\nResurs ima važnu ulogu u poslovnim procesima organizacije ili je dio zakonskih i/ili regulatornih obaveza.\n");

					stringBuilder.append("<b>4-Kritièna<b>\nResurs ima kritiènu ulogu u poslovnim procesima organizacije ili je dio zakonskih i/ili regulatornih obaveza.\n");

					stringBuilder.append("<b>5-Vrlo kritièna</b>\nResurs ima vrlo kritiènu ulogu u procesu ili je kritièna za usklaðivanje sa zakonskim i/ili regulatorskim propisima.");
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
