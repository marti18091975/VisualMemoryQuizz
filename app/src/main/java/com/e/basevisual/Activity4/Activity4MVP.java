package com.e.basevisual.Activity4;

import android.content.Intent;
import android.view.View;

import com.e.basevisual.Objects.Usuari;

import java.util.List;

public interface Activity4MVP {
    interface view{
        void omplir_recycler(List<Usuari> classificacio,Usuari usuari);
        void mostrar_error();
    }
    interface presenter{

        void setView(Activity4MVP.view view,Usuari usuari);
    }
    interface model{
        void obtenir_classificacio(Activity4Model.detallsCallback callback, Usuari usuari);

    }
}
