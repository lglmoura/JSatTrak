// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PacketSizeControl.java

package javax.media.control;

import javax.media.Control;

public interface PacketSizeControl
    extends Control
{

    public abstract int setPacketSize(int i);

    public abstract int getPacketSize();
}
