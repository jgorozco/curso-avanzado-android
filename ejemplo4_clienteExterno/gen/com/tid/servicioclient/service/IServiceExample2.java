/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/josevk/Dropbox/tid/aurigae/workspace_curso/ejemplo4_clienteExterno/src/com/tid/servicioclient/service/IServiceExample2.aidl
 */
package com.tid.servicioclient.service;
public interface IServiceExample2 extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.tid.servicioclient.service.IServiceExample2
{
private static final java.lang.String DESCRIPTOR = "com.tid.servicioclient.service.IServiceExample2";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.tid.servicioclient.service.IServiceExample2 interface,
 * generating a proxy if needed.
 */
public static com.tid.servicioclient.service.IServiceExample2 asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.tid.servicioclient.service.IServiceExample2))) {
return ((com.tid.servicioclient.service.IServiceExample2)iin);
}
return new com.tid.servicioclient.service.IServiceExample2.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_PingServicio:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.PingServicio();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_killService:
{
data.enforceInterface(DESCRIPTOR);
this.killService();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.tid.servicioclient.service.IServiceExample2
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public java.lang.String PingServicio() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_PingServicio, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public void killService() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_killService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_PingServicio = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_killService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public java.lang.String PingServicio() throws android.os.RemoteException;
public void killService() throws android.os.RemoteException;
}
