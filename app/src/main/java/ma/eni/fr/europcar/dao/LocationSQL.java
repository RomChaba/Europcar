package ma.eni.fr.europcar.dao;

import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Rendu;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class LocationSQL implements ILocationDAO
{
    @Override
    public boolean louer(Location location)
    {
        return false;
    }

    @Override
    public boolean rendre(Rendu rendu)
    {
        return false;
    }
}
