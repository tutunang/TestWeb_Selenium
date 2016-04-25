package com.elong.air.tools;
/**
 * @author  qiaojiafei
 * @version 创建时间：2016年4月25日 下午1:53:40
 * 类说明
 */
public enum Priority {
	P1(){
		@Override
        public String toString() {
            return "P1";
        }
	},
	P2(){
		@Override
        public String toString() {
            return "P2";
        }
	},
	P3(){
		@Override
        public String toString() {
            return "P3";
        }
	},
	ALL(){
		@Override
        public String toString() {
            return "ALL";
        }
	};
    Priority() {
    }
}
