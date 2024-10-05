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
public void saveMember(Members e) {
	  
	impMembers.save(e);
}
public void deleteMember(Members e) {
	impMembers.delete(e);
}
public void updateMember(Members e) {
	impMembers.update(e);
}
public void dleteMember(Members e) {
	impMembers.update(e);
}
public List<Members> getMemberPage(int page, int pageSize) {
    return impMembers.getPage(page, pageSize);
}

public int getTotalPages(int pageSize) {
    long totalMembers = impMembers.count();
    return (int) Math.ceil((double) totalMembers / pageSize);
}
public Members getMemberById(int id) {
	return impMembers.findById(id);
}
}
