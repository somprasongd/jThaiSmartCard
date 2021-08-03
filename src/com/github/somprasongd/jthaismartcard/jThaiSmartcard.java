/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.somprasongd.jthaismartcard;

import com.github.somprasongd.jthaismartcard.converter.SpecPropertyReader;
import com.github.somprasongd.jthaismartcard.model.NhsoInfo;
import com.github.somprasongd.jthaismartcard.model.OldPersonalInfo;
import com.github.somprasongd.jthaismartcard.model.PersonalInfo;
import com.github.somprasongd.jthaismartcard.util.ByteUtils;
import com.github.somprasongd.jthaismartcard.util.CardUtils;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;

/**
 *
 * @author sompr
 */
public class jThaiSmartcard {

    private static final Logger LOG = Logger.getLogger(jThaiSmartcard.class.getName());

    //encode of card.
    private final byte[] selectOldMoiCommand = ByteUtils.stoh("00A4040008A000000054480011");
    private final byte[] selectMoiCommand = ByteUtils.stoh("00A4040008A000000054480001");
    private final byte[] selectNHSOCommand = ByteUtils.stoh("00A4040008A000000054480083");

    public jThaiSmartcard() {
    }

    public List<String> getTerminalNames() {
        List<String> cardTerminalNames = CardUtils.getCardTerminalNames();
        return cardTerminalNames;
    }

    public String getPresentTerminalName() {
        List<String> cardTerminalNames = CardUtils.getCardTerminalNames();
        return cardTerminalNames.isEmpty() ? null : cardTerminalNames.get(0);
    }

    public PersonalInfo read() {
        return read(true, true);
    }

    public PersonalInfo read(boolean withPicture, boolean withNhso) {
        return read((String) null, withPicture, withNhso);
    }

    public PersonalInfo read(String cardName, boolean withPicture, boolean withNhso) {
        CardTerminal cardTerminal;
        if (cardName == null || cardName.isEmpty()) {
            cardTerminal = CardUtils.getFirstCardTerminalPresent();
        } else {
            cardTerminal = CardUtils.getCardTerminalByName(cardName);
        }
        return read(cardTerminal, withPicture, withNhso);
    }

    public PersonalInfo read(CardTerminal cardTerminal, boolean withPicture, boolean withNhso) {
        if (cardTerminal == null) {
            LOG.info("No card terminal");
            return null;
        }
        PersonalInfo personalInfo = new PersonalInfo();
        try {
            Card card = cardTerminal.connect("*");
            CardChannel channel = card.getBasicChannel();
            CommandAPDU commands = new CommandAPDU(selectOldMoiCommand);
            ResponseAPDU response = channel.transmit(commands);
            if (response.getSW() == 9000) {
                personalInfo.setOldCard(true);
                personalInfo.setCardType(24);
                commands = new CommandAPDU(selectMoiCommand);

                //transmit byte value to chanel and send value to readAnfillData method.
                channel.transmit(commands);
                CardUtils.readAndfillData(card, SpecPropertyReader.getInstance().getSpecs(OldPersonalInfo.class), personalInfo, withPicture);

            } else {
                personalInfo.setOldCard(false);
                personalInfo.setCardType(26);
                commands = new CommandAPDU(selectMoiCommand);

                //transmit byte value to chanel and send value to readAnfillData method.
                channel.transmit(commands);

                CardUtils.readAndfillData(card, SpecPropertyReader.getInstance().getSpecs(PersonalInfo.class), personalInfo, withPicture);

                if (withNhso) {
                    commands = new CommandAPDU(selectNHSOCommand);

                    //transmit byte value to chanel and send value to readAnfillData method.
                    channel.transmit(commands);

                    NhsoInfo nhsoInfo = new NhsoInfo();

                    CardUtils.readAndfillData(card, SpecPropertyReader.getInstance().getSpecs(NhsoInfo.class), nhsoInfo, false);

                    personalInfo.setNhsoInfo(nhsoInfo);
                }
            }
            LOG.info("Read completed");
            // เมื่อเครื่องอ่านสมาร์ทการ์ดอ่านเสร็จก็จะทำการ Disconnect
            card.disconnect(true);
        } catch (CardException | IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            personalInfo = null;
        }
        return personalInfo;
    }

}
