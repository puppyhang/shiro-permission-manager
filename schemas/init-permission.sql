/*初始化权限数据的脚本*/
INSERT INTO sys_role VALUES
  (replace(uuid(),'-',''),'admin','admin',now(),0,'管理员角色'),
  (replace(uuid(),'-',''),'root','root',now(),0,'ROOT用户角色'),
  (replace(uuid(),'-',''),'user','user',now(),0,'普通用户角色');