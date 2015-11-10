package com.elong.air.dataProvider;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class PraseXml {
	  private Document document;
      private String filePath;

      public PraseXml(String filePath) {
              // TODO Auto-generated constructor stub
              this.filePath = filePath;
              this.load(this.filePath);
      }

      /**
       * @param args
       */
      public static void main(String[] args) {

              // TODO Auto-generated method stub
              String file = "src/config/test.xml";
              PraseXml px = new PraseXml(file);

              // xml解析
              String browser = px.getElementText("config/browser");
              System.out.println(browser);

      }

      /*
       * 假造xml文件，并产生一个document对象
       */
      private void load(String filePath) {
              File file = new File(filePath);
              if (file.exists()) {
                      SAXReader saxReader = new SAXReader();
                      try {
                              document = saxReader.read(file);
                      } catch (DocumentException e) {
                              // TODO Auto-generated catch block
                              System.out.println("文件加载异常： " + filePath);
                      }
              } else {
                      System.out.println("文件不存在： " + filePath);
              }
      }

      /*
       * 用xpath来得到一个元素节点对象
       * 
       * @param elementPath elementPath是一个xpath路径，比如“/config/driver”
       * 
       * @return 返回一个节点Element对象
       */
      public Element getElementObject(String elementPath) {
              return (Element) document.selectSingleNode(elementPath);
      }

      /*
       * 用xpath来得到一个结点对象的值
       */
      public String getElementText(String elementPath) {
              Element element = this.getElementObject(elementPath);
              if (element != null) {
                      return element.getText().trim();
              } else {
                      return null;
              }
      }

      /*
       * 用xpath来判断一个结点对象是否存在
       */
      public boolean isExists(String elementPath) {
              boolean flag = false;
              Element element = this.getElementObject(elementPath);
              if (element != null) {
                      flag = true;
              }
              return flag;
      }

      // 加@是为了无视方法里边出现的警告
      @SuppressWarnings("unchecked")
      public List<Element> getElementObjects(String elementPath) {
              return document.selectNodes(elementPath);
      }

      @SuppressWarnings("unchecked")
      public Map<String, String> getChildrenInfoByElement(Element element) {
              Map<String, String> map = new HashMap<String, String>();
              List<Element> children = element.elements();
              for (Element e : children) {
                      map.put(e.getName(), e.getText());
              }
              return map;
      }
}