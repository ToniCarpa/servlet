package service;

import dao.Dao;
import dao.Jdbc;
import model.Usuario;

public class SesionService {
    private Dao dao;
    private Jdbc jdbc;

    private static SesionService sesionService;

    public static  SesionService sesionService(){
        if(sesionService == null)
            sesionService = new SesionService();
        return sesionService;
    }

    // LOGIN -> REGISTER && HOME && RECOVER
    // user exist(userlist(mail))->true || register



    // REGSITER
    //user check(userlist(mail)->isValid(!name)true || login



    // RECOVER MAILING



    // HOME -> USERPOSTS || ALLPOSTS



    // USERPOSTS -> ( CREATE & DELETE & SELECT ) POSTS + GALLERYVIEW



    // ALLPOSTS -> ( SELECT & LIKE ) + GALLERYVIEW





}
