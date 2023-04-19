package com.jiho.shopping.service;

import com.jiho.shopping.entity.Item;
import com.jiho.shopping.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    public void saveItem(Item item, MultipartFile img)throws Exception{
        String rawImgName = img.getOriginalFilename();
        String imgName = "";
        String sysPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid + "_" + rawImgName;

        imgName = saveFileName;

        File saveFile = new File(sysPath, imgName);

        img.transferTo(saveFile);

        item.setImageName(imgName);
        item.setImagePath("/files/"+imgName);



        itemRepository.save(item);
    }
    public Item itemView(Integer id){

        return itemRepository.findById(id).get();
    }

    public List<Item> allItemView(){
        return itemRepository.findAll(Sort.by(Sort.Direction.ASC,"uploadedAt"));
    }

    public List<Item> findItemByCategory(String category){

            return itemRepository.findItemByCategory(category);
    }

    public void itemModify(Item item, Integer id, MultipartFile img) throws Exception{
        String rawImgName = img.getOriginalFilename();
        String imgName = "";
        String sysPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid + "_" + rawImgName;

        imgName = saveFileName;

        File saveFile = new File(sysPath, imgName);

        img.transferTo(saveFile);

        Item update = itemRepository.findItemById(id);

        update.setName(item.getName());
        update.setContents(item.getContents());
        update.setPrice(item.getPrice());
        update.setCategory(item.getCategory());
        update.setTitle(item.getTitle());
        update.setImageName(imgName);
        update.setImagePath("/files/"+imgName);

        itemRepository.save(update);
    }

    public void itemDelete(Integer id){
        itemRepository.deleteById(id);
    }
}
