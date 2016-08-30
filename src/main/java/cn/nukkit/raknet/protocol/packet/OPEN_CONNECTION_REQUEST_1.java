package cn.nukkit.raknet.protocol.packet;

import cn.nukkit.raknet.RakNet;
import cn.nukkit.raknet.protocol.Packet;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class OPEN_CONNECTION_REQUEST_1 extends Packet {
    public static final byte ID = (byte) 0x05;

    @Override
    public byte getID() {
        return ID;
    }

    public byte protocol = RakNet.PROTOCOL;
    public int mtuSize;

    @Override
    public void encode() {
        super.encode();
        this.put(RakNet.MAGIC);
        this.putByte(this.protocol);
        this.put(new byte[this.mtuSize - 18]);
    }

    @Override
    public void decode() {
        super.decode();
        this.offset += 16; //skip magic bytes
        this.protocol = this.getByte();
        this.mtuSize = this.buffer.length;
    }

    public static final class Factory implements Packet.PacketFactory {

        @Override
        public Packet create() {
            return new OPEN_CONNECTION_REQUEST_1();
        }

    }

}
