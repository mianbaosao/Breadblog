package com.itmianbao.utils;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.itmianbao.mapper.BlogRepository;
import com.itmianbao.pojo.BlogData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Description: 数据同步es
 * @Author:bread
 * @Date: 2024-05-26 16:35
 */
@Component
public class CanalRunner implements CommandLineRunner {

    @Resource
    private BlogRepository blogRepository;
    @Override
    public void run(String... args) throws Exception {
        startCanal();
    }

    private void startCanal() throws InvalidProtocolBufferException {
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(new InetSocketAddress("localhost", 11111), "example", "root", "he13140303042");
        while (true) {
            canalConnector.connect();
            canalConnector.subscribe("db01.*");
            Message message = canalConnector.get(100);
            List<CanalEntry.Entry> entries = message.getEntries();
            if (entries.size() <= 0) {
                //System.out.println("没有数据，休息一会");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                for (CanalEntry.Entry entry : entries) {
                    CanalEntry.EntryType entryType = entry.getEntryType();
                    if (CanalEntry.EntryType.ROWDATA.equals(entryType)) {
                        ByteString storeValue = entry.getStoreValue();
                        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);
                        CanalEntry.EventType eventType = rowChange.getEventType();
                        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
                        for (CanalEntry.RowData rowData : rowDatasList) {
                            BlogData blogData = convertToBlogData(rowData);

                            if(blogData.getAllowIssue().equals("1")){
                                blogData.setAllowIssue("true");
                            }else{
                                blogData.setAllowIssue("false");
                            }
                            System.out.println(blogData);
                            blogRepository.save(blogData);
                            // 此处调用保存到ES的方法，例如：saveToES(blogData);
                        }
                    }
                }
            }
        }
    }

    private BlogData convertToBlogData(CanalEntry.RowData rowData) {
        BlogData blogData = new BlogData();
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            String columnName = column.getName();
            String columnValue = column.getValue();
            if (columnValue != null && !columnValue.isEmpty()) {
                switch (columnName) {
                    case "blogId":
                        blogData.setBlogId(Integer.valueOf(columnValue));
                        break;
                    case "cover":
                        blogData.setCover(columnValue);
                        break;
                    case "title":
                        blogData.setTitle(columnValue);
                        break;
                    case "writer":
                        blogData.setWriter(columnValue);
                        break;
                    case "categoryName":
                        blogData.setCategoryName(columnValue);
                        break;
                    case "allowComment":
                        blogData.setAllowComment(columnValue);
                        break;
                    case "statusName":
                        blogData.setStatusName(columnValue);
                        break;
                    case "status":
                        blogData.setStatus(columnValue);
                        break;
                    case "content":
                        blogData.setContent(columnValue);
                        break;
                    case "categoryId":
                        blogData.setCategoryId(Integer.valueOf(columnValue));
                        break;
                    case "description":
                        blogData.setDescription(columnValue);
                        break;
                    case "allowIssue":
                        blogData.setAllowIssue(columnValue);
                        break;
                    case "time":
                        blogData.setTime(columnValue);
                        break;
                    case "browse":
                        blogData.setBrowse(Integer.valueOf(columnValue));
                        break;
                    case "isLike":
                        blogData.setIsLike(Integer.valueOf(columnValue));
                        break;
                    case "likes":
                        blogData.setLikes(Integer.valueOf(columnValue));
                        break;
                    default:
                        // 可以根据需要处理其他字段，或者忽略
                        break;
                }
            }
        }
        return blogData;
    }

}
