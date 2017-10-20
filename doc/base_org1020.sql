CREATE TABLE base_org
(
  id         VARCHAR(32)      NOT NULL
    PRIMARY KEY,
  name       VARCHAR(32)      NULL,
  code       VARCHAR(32)      NULL
  COMMENT '编码  唯一校验',
  level_code VARCHAR(128)     NULL
  COMMENT '层级编码',
  org_type   VARCHAR(32)      NULL
  COMMENT '数据字典ID',
  deleted    CHAR DEFAULT 'N' NULL
  COMMENT 'Y删除 N不删除',
  note       VARCHAR(512)     NULL
  COMMENT '备注',
  parentid   VARCHAR(32)      NULL
  COMMENT '上级ID',
  lspell     VARCHAR(32)      NULL
  COMMENT '名称全拼',
  uspell     VARCHAR(32)      NULL,
  initials   VARCHAR(32)      NULL
  COMMENT '首字母'
);


INSERT INTO esp_admin.base_org (id, name, code, level_code, org_type, deleted, note, parentid, lspell, uspell, initials) VALUES ('14ce148bb2ea11e7a4000242ac110003', '组织机构A', '00010A', '000001000001000002', 'key_one', 'N', 'ffff', 'AA', 'zuzhijigoua', 'ZUZHIJIGOUA', 'zzjga_ZZJGA');
INSERT INTO esp_admin.base_org (id, name, code, level_code, org_type, deleted, note, parentid, lspell, uspell, initials) VALUES ('871d4136b30611e7a4000242ac110003', '测试机构', '001205456', '000001000001000004', 'key_three', 'N', '测试A法', 'AA', 'ceshijigou', 'CESHIJIGOU', 'csjg_CSJG');
INSERT INTO esp_admin.base_org (id, name, code, level_code, org_type, deleted, note, parentid, lspell, uspell, initials) VALUES ('A', '组织机构', '000010', '000001', 'key_one', 'N', '行政组织机构', '0', 'zuzhijigou', 'ZUZHIJIGOU', 'zzjg_ZZJG');
INSERT INTO esp_admin.base_org (id, name, code, level_code, org_type, deleted, note, parentid, lspell, uspell, initials) VALUES ('AA', '测试组织结构AA', 'AA', '000001000001', 'key_four', 'N', 'b', 'A', 'ceshizuzhijiegouaa', 'CESHIZUZHIJIEGOUAA', 'cszzjgaa_CSZZJGAA');
INSERT INTO esp_admin.base_org (id, name, code, level_code, org_type, deleted, note, parentid, lspell, uspell, initials) VALUES ('bfe49e65b30811e7a4000242ac110003', 'hzq测试', '01101010', '000001000001000005', 'key_three', 'N', '测试机构', 'AA', 'hzqceshi', 'HZQCESHI', 'hzqcs_HZQCS');
