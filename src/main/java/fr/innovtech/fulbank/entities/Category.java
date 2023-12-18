package fr.innovtech.fulbank.entities;

public class Category {

        private int _id;
        private String _wording;
        private Float _account_fees;
        private Float _ceiling_high;
        private Float _low_ceiling;
        private String  _currency;
        private Crypto _crypto;

        private Float _interest_rate;

        public Category(Integer _id, String _wording, Float _account_fees, Float _ceiling_high, Float _low_ceiling,Float _interest_rate, String _currency, Crypto _crypto) {
            this._wording = _wording;
            this._account_fees = _account_fees;
            this._ceiling_high = _ceiling_high;
            this._low_ceiling = _low_ceiling;
            this._currency = _currency;
            this._crypto = _crypto;
            this._id = _id;
            this._interest_rate = _interest_rate;
        }

        public String get_wording() {
            return _wording;
        }

        public Float get_account_fees() {
            return _account_fees;
        }

        public Float get_ceiling_high() {
            return _ceiling_high;
        }

        public Float get_low_ceiling() {
            return _low_ceiling;
        }

        public String get_currency() {
            return _currency;
        }

        public void set_wording(String _wording) {
            this._wording = _wording;
        }

        public void set_account_fees(Float _account_fees) {
            this._account_fees = _account_fees;
        }

        public void set_ceiling_high(Float _ceiling_high) {
            this._ceiling_high = _ceiling_high;
        }

        public void set_low_ceiling(Float _low_ceiling) {
            this._low_ceiling = _low_ceiling;
        }

        public void set_currency(String _currency) {
            this._currency = _currency;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public Crypto get_crypto() {
            return _crypto;
        }

        public void set_crypto(Crypto _crypto) {
            this._crypto = _crypto;
        }

        public Float get_interest() {
            return _interest_rate;
        }

        public void set_interest(Float _interest_rate) {
            this._interest_rate = _interest_rate;
        }





        @Override
        public String toString() {
            return "Category{" +
                    "_id=" + _id +
                    ", _wording='" + _wording + '\'' +
                    ", _account_fees=" + _account_fees +
                    ", _ceiling_high=" + _ceiling_high +
                    ", _low_ceiling=" + _low_ceiling +
                    ", _currency=" + _currency +
                    ", _crypto=" + _crypto +
                    '}';
        }



}
