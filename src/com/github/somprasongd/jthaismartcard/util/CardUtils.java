/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.somprasongd.jthaismartcard.util;

import com.github.somprasongd.jthaismartcard.Main;
import com.github.somprasongd.jthaismartcard.converter.Converter;
import com.github.somprasongd.jthaismartcard.converter.ConverterFactory;
import com.github.somprasongd.jthaismartcard.converter.Spec;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

/**
 *
 * @author sompr
 */
public class CardUtils {

    private static final Logger LOG = Logger.getLogger(CardUtils.class.getName());

    public static List<String> getCardTerminalNames() {
        List<String> cardNames = new ArrayList<>();
        try {
            TerminalFactory tf = TerminalFactory.getDefault();
            List<CardTerminal> list = tf.terminals().list();
            for (CardTerminal cardTerminal : list) {
                if (cardTerminal.isCardPresent()) {
                    cardNames.add(0, cardTerminal.getName());
                } else {
                    cardNames.add(cardTerminal.getName());
                }
            }
        } catch (CardException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cardNames;
    }

    public static List<CardTerminal> listCardTerminals() {
        List<CardTerminal> cardNames = new ArrayList<>();
        try {
            TerminalFactory tf = TerminalFactory.getDefault();
            List<CardTerminal> list = tf.terminals().list();
            for (CardTerminal cardTerminal : list) {
                if (cardTerminal.isCardPresent()) {
                    cardNames.add(0, cardTerminal);
                } else {
                    cardNames.add(cardTerminal);
                }
            }
        } catch (CardException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cardNames;
    }

    public static List<CardTerminal> listCardTerminalPresents() {
        try {
            TerminalFactory tf = TerminalFactory.getDefault();
            List<CardTerminal> list = tf.terminals().list(CardTerminals.State.CARD_PRESENT);
            return list;
        } catch (CardException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<CardTerminal> listCardTerminalInserts() {
        try {
            TerminalFactory tf = TerminalFactory.getDefault();
            List<CardTerminal> list = tf.terminals().list(CardTerminals.State.CARD_INSERTION);
            return list;
        } catch (CardException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public static CardTerminal getCardTerminalInserted() {
//        try {
//            TerminalFactory tf = TerminalFactory.getDefault();
//            List<CardTerminal> list = tf.terminals().list(CardTerminals.State.CARD_INSERTION);
//            return list.isEmpty() ? null : list.get(0);
//        } catch (CardException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    public static CardTerminal getFirstCardTerminalPresent() {
        try {
            TerminalFactory tf = TerminalFactory.getDefault();
            List<CardTerminal> list = tf.terminals().list(CardTerminals.State.CARD_PRESENT);
            return list.isEmpty() ? null : list.get(0);
        } catch (CardException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static CardTerminal getCardTerminalByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        try {
            TerminalFactory tf = TerminalFactory.getDefault();
            List<CardTerminal> list = tf.terminals().list(CardTerminals.State.ALL);
            for (CardTerminal cardTerminal : list) {
                if (cardTerminal.getName().equals(name)) {
                    return cardTerminal;
                }
            }
        } catch (CardException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void readAndfillData(Card card, List<Spec> specs, Object bean, boolean withPicture) throws CardException {
        //Call CardChanell of libary of Card.
        CardChannel channel = card.getBasicChannel();

        for (Spec spec : specs) {
            if ((withPicture)
                    || (!spec.getSettingMethod().getName().equals("setFacesImage"))) {

                ByteBuffer allocate;

                if (spec.getApduCommands().length > 1) {

                    allocate = ByteBuffer.allocate(5120);

                    for (byte[] apduCommand : spec.getApduCommands()) {
                        CommandAPDU command = new CommandAPDU(apduCommand);
                        //check error of bytecode.
                        if (LOG.isLoggable(Level.FINEST)) {
                            LOG.log(Level.INFO, "<-------{0}", ByteUtils.htos(command.getBytes()));
                        }
                        //trnsmit of bytecode.
                        ResponseAPDU response = channel.transmit(command);
                        allocate.put(response.getData());
                    }
                } else {
                    allocate = ByteBuffer.allocate(256);

                    CommandAPDU command = new CommandAPDU(spec.getApduCommands()[0]);

                    //check error of bytecode.
                    if (LOG.isLoggable(Level.FINEST)) {
                        LOG.log(Level.INFO, "<-------{0}", ByteUtils.htos(command.getBytes()));
                    }

                    //trnsmit of bytecode.
                    ResponseAPDU response = channel.transmit(command);
                    allocate.put(response.getData());
                }

                //Call method set such as setFaceImage(),setPID() etc.
                Method settingMethod = spec.getSettingMethod();
                try {
                    //send settingMethod to kept in interfaceClass of Converter.
                    ConverterFactory converterFact = new ConverterFactory();
                    Converter converter = converterFact.getConverter(settingMethod.getParameterTypes()[0]);

                    settingMethod.invoke(bean, new Object[]{converter.toObject(allocate.array())});
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    //check value null or other.
                    LOG.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
        }
    }
}
