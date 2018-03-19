package steel.marnix.eindwerkandroidstreats.TempdataSource;

import com.google.android.gms.maps.model.LatLng;

import steel.marnix.eindwerkandroidstreats.model.StreetArt;

/**
 * Created by mazze on 19/03/2018.
 */

public class MockupDAO {

    private static final MockupDAO ourInstance = new MockupDAO();

    public static MockupDAO getInstance(){
        return ourInstance;
    }

    private MockupDAO (){
    }

    public StreetArt[] getArtData(){

        LatLng artCoord1 = new LatLng(50.8440974, 4.3488076);
        StreetArt mannekenPeace = new StreetArt("fa518a1373a3c87687f34bb5992bb08facd8bdcf", "Manneken Peace", "H.M.I./Collectif CNN", "Op de puntgevel aan de zijkant van de Eikstraat 19", "STREETART", artCoord1);
        LatLng artCoord2 = new LatLng(50.839982, 4.36068);
        StreetArt koolKoor = new StreetArt("d0bb70f35c72f7bdc4af4f3cd8900f88f53f5ab3", "Untitled", "Kool Koor", "Naamsestraat 64", "STREETART", artCoord2);
        LatLng artCoord3 = new LatLng(50.839964, 4.360147);
        StreetArt parole = new StreetArt("a5bfde5812fef348c35981529559c6e68ff71ee0", "Untitled", "Parole", "Kruispunt Naamsestraat x BaudetStraat", "STREETART", artCoord3);

        StreetArt[] streetArts = {mannekenPeace, koolKoor, parole};
        return streetArts;
    }
}
