package com.sydl.electronic.endecoder;

import com.sydl.electronic.constants.ConstantValue;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ElectricSensorServerDecoder extends ByteToMessageDecoder {

    int a=0;

    /**
     * 数据包基本长度
     * 请求解码器
     * <pre>
     * 数据包格式
     * +——----——+——-----——+——----——+——----——+——-----——+
     * | 包头          | 模块号        | 命令号      |  长度        |   数据       |
     * +——----——+——-----——+——----——+——----——+——-----——+
     * </pre>
     * 包头4字节
     * 模块号2字节short
     * 命令号2字节short
     * 长度4字节(描述数据部分字节长度)
     **/
    public static int BASE_LENTH = 4 + 2 + 2 + 4;

    //ChannelBuffer里面有一个读指针和写指针。读指针和写指针初始值是0，写多少数据写指针就移动多少
    //调用readShort方法，readInt方法就会移动读指针， 0 =< readerIndex =< writerIndex
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("开始解码");
        //是否小于最小的长度,否则返回等待下一次
        if(in.readableBytes() >= BASE_LENTH){ //最少要12byte数据才开始读
            //防止socket字节流攻击 不得超过2M
//            if(in.readableBytes() > 2048){
//                in.skipBytes(in.readableBytes());
//            }
            //记录包头开始的index
            int beginReader;

            while(true){   //循环读取，直到包头标志位读取完毕  这个死循环只处理包头标识位
                beginReader = in.readerIndex();//获取读指针
                in.markReaderIndex(); //bytebuffer里面mark一下位置
                if(in.readInt() == ConstantValue.FLAG){ //是否等于包头标志位
                    break; //是的话直接退出死循环
                }

                //未读到包头，1.恢复标志mark位 2.略过到下一个字节，继续新一轮的读
                in.resetReaderIndex();
                in.readByte();

                //新一轮读事前，在检查一下是否 长度又变得不满足
                if(in.readableBytes() < BASE_LENTH){

                    return; //等待后面的数据包来
                }
            }

            //找到了包头
            //包头读取完毕，读取模块号
            short module = in.readShort();
            //读取命令号
            short cmd = in.readShort();
            //读取长度
            int length = in.readInt();

            //readableBytes现在可读的长度小于数据的长度。判断请求数据包数据部分是否到齐
            if(in.readableBytes() < length){
                //还原读指针，已经读取了12个字节，但是没用，所以要还原buffer的读指针，
                in.readerIndex(beginReader);
                System.out.println("***********************长度不满足");
                return;//等待后面的数据包来
            }

            //这里整个消息长度为12+n，跳过当前消息，增大bufferIn的readindex，bufferIn中数组可复用
            in.readerIndex(beginReader + 12 +length);

            //Returns a slice of this buffer's sub-region.
            //取出当前的整条消息并存入otherByteBufRef中
            ByteBuf otherByteBufRef = in.slice(beginReader, 12 + length);

            /**
             * 1.每一个bytebuf都有一个计数器，每次调用计数器减1，当计数器为0时则不可用。
             * 2.当前bytebuf中数据包含多条消息，本条信息会通过out返回被继续封装成一个新的bytebuf返回下一个hander处理
             * 3.retain方法是将当前的bytebuf计数器加1
             * */
            otherByteBufRef.retain();

            out.add(otherByteBufRef);
            System.out.println("解码完毕"+ (++a));


        }else{
            System.out.println("*****************等待后面的数据包来");
            //小于最低长度直接return下一次,等待后面的数据包来
            return;
        }


    }

}
