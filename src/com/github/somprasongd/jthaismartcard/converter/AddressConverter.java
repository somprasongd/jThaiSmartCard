package com.github.somprasongd.jthaismartcard.converter;

import com.github.somprasongd.jthaismartcard.model.Address;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressConverter implements Converter {

    private final String[] ADDR = new String[]{"������", "���", "���", "�Ӻ�", "�����", "�ǧ", "ࢵ", "�ѧ��Ѵ"};
    private final Pattern p = Pattern.compile("\\d*/*\\d*");

    @Override
    public Object toObject(byte[] src) {
        String[] addrs = new String(src, TIS620).trim().split("#");
        Address address = new Address();
        String fullAddress = "";
        process:
        for (String addr : addrs) {
            if (addr.trim().isEmpty()) {
                continue;
            }
            fullAddress += addr + " ";
            Matcher m = p.matcher(addr);
            if (m.matches()) {
                address.setHouseNo(addr);
                continue;
            }
            for (String ADDR1 : ADDR) {
                if (addr.startsWith(ADDR1)) {
                    setValue(address, ADDR1, addr);
                    continue process;
                }
            }
        }
        address.setAddress(fullAddress.trim());
        return address;
    }

    private void setValue(Address address, String key, String value) {
        String val = value.substring(key.length()).trim();
        switch (key) {
            case "������":
                address.setMoo(val);
                break;
            case "���":
                address.setSoi(val);
                break;
            case "���":
                address.setRoad(val);
                break;
            case "�Ӻ�":
                address.setSubdistrict(val);
                break;
            case "�����":
                address.setDistrict(val);
                break;
            case "�ǧ":
                address.setSubdistrict(val);
                break;
            case "ࢵ":
                address.setDistrict(val);
                break;
            case "�ѧ��Ѵ":
                address.setProvince(val);
                break;
            default:
        }
    }
}
