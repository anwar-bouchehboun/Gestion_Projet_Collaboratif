package Projet_Calbo.services;

import java.util.List;

import Projet_Calbo.model.Members;
import Projet_Calbo.repositories.ProjetRepositoryImpl.MemberImp;

public class MemberService {
	
	
public 	MemberImp impMembers=new MemberImp();
public MemberService() {
}

public List<Members> getAll(){
	return impMembers.getAll();
}
public void saveEquipe(Members e) {
	  
	impMembers.save(e);
}
public void deleteEquipe(Members e) {
	impMembers.delete(e);
}
public void updateEquipe(Members e) {
	impMembers.update(e);
}
public List<Members> getMemberPage(int page, int pageSize) {
    return impMembers.getPage(page, pageSize);
}

public int getTotalPages(int pageSize) {
    long totalMembers = impMembers.count();
    return (int) Math.ceil((double) totalMembers / pageSize);
}

}
