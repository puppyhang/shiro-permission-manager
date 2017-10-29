/*初始化权限数据的脚本*/
INSERT INTO sys_role VALUES
  (replace(uuid(), '-', ''), 'admin', 'admin', now(), 0, '管理员角色'),
  (replace(uuid(), '-', ''), 'root', 'root', now(), 0, 'ROOT用户角色'),
  (replace(uuid(), '-', ''), 'user', 'user', now(), 0, '普通用户角色');

INSERT INTO sys_resource VALUES
  (replace(uuid(),'-',''),'system:menu:manage','系统管理','#',null,null,1,now(),'0','系统管理菜单'),
  (replace(uuid(),'-',''),'system:menu:product:manage','产品管理','#',null,null,2,now(),'0','产品管理菜单');