package com.pascal.game.Conversations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Timer;
import com.pascal.game.Quests.QuestTexts;

public class ConversationHandler {
    private Skin skin;
    private Stage stage;
    Dialog dialog;

    public ConversationHandler(Skin skin, Stage stage) {
        this.skin = skin;
        this.stage = stage;
        Gdx.input.setInputProcessor(stage);
    }

    public void showNPCInteractionDialog() {
         dialog = new Dialog("webos:", skin) {
            public void result(Object obj) {
                System.out.println("Respuesta: " + obj);
            }
        };

        dialog.text(new Label(QuestTexts.NPC_INTERACTION_TEXT, skin));
        dialog.button(new TextButton("Claudia GODSheinbaum", skin), "claudia");
        dialog.button(new TextButton("Xochitl KKGálvez", skin), "xochitl");
        dialog.button(new TextButton("Jajorge Mamaynez", skin), "xd");
        dialog.show(stage);
    }
    public void showNPCInteractionDialog(String npc_Name){
        if(npc_Name.toLowerCase().equals("figueroa")){

            dialog = new Dialog(npc_Name + " : ", skin) {
                @Override
                protected void result(Object object) {
                    // Cerrar el diálogo actual
                    this.hide();
                    // Crear un nuevo diálogo con el contenido actualizado
                    Dialog response = new Dialog(npc_Name + " : ", skin);
                    response.getBackground().setMinHeight(100);
                    response.getBackground().setMinWidth(150);
                    if(object.equals("xochitl") || object.equals("maynez")){
                        response.text(new Label("Ni modo, regresa cuando aprendas a no votar por: " + object.toString(), skin));
                        response.button("Salir", true);
                        // Mostrar el nuevo diálogo
                        response.show(stage);
                    }else{
                        response.text(new Label("Al menos te fuiste por la 'menos pior' ", skin));
                        response.button("Salir", true);
                        // Mostrar el nuevo diálogo
                        response.show(stage);
                    }
                }
            };

            dialog.text(new Label("¿Por Quien vas a Votar?", skin));
            dialog.button("Claudia", "claudia");
            dialog.button("Xochitl", "xochitl");
            dialog.button("Mamaynez", "maynez");
            dialog.show(stage);
        }
    }

}

