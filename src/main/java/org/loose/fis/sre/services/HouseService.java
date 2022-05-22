package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.House;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.exceptions.HouseDoesNotExistsException;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;
import static org.loose.fis.sre.services.FileSystemService.getPathToHouse;

public class HouseService {

    private static ObjectRepository<House> houseRepository;

    public static void initDatabase() {
        FileSystemService.initDirectory_house();
        Nitrite database = Nitrite.builder()
                .filePath(getPathToHouse("house-database.db").toFile())
                .openOrCreate();
        houseRepository = database.getRepository(House.class);
    }
    public static ObjectRepository<House> getHouseRepository()
    {
        return houseRepository;
    }

    public static String  seeHouses()
    {
        String s="";
        for (House house : houseRepository.find())
        {

            s = s + house.toString();
            s = s + "\n";
        }
        return s;
    }

    public static String searchHouse(String address) throws HouseDoesNotExistsException
    {
        for (House house : houseRepository.find())
        {
            if(Objects.equals(address, house.getAddress())) {
                return house.toString();
            }
        }
        throw new HouseDoesNotExistsException(address);
    }
}