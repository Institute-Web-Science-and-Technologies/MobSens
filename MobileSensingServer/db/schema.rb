# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20131127222010) do

  create_table "accelerometers", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.float    "x"
    t.float    "y"
    t.float    "z"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "accelerometers", ["recording_id"], name: "index_accelerometers_on_recording_id"

  create_table "annotations", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.string   "value"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "annotations", ["recording_id"], name: "index_annotations_on_recording_id"

  create_table "batteries", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.integer  "level"
    t.integer  "scale"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "batteries", ["recording_id"], name: "index_batteries_on_recording_id"

  create_table "devices", force: true do |t|
    t.string   "identifier"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "gravities", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.float    "x"
    t.float    "y"
    t.float    "z"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "gravities", ["recording_id"], name: "index_gravities_on_recording_id"

  create_table "gyroscopes", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.float    "rX"
    t.float    "rY"
    t.float    "rZ"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "gyroscopes", ["recording_id"], name: "index_gyroscopes_on_recording_id"

  create_table "lights", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.float    "ambientLight"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "lights", ["recording_id"], name: "index_lights_on_recording_id"

  create_table "linear_accelerations", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.float    "x"
    t.float    "y"
    t.float    "z"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "linear_accelerations", ["recording_id"], name: "index_linear_accelerations_on_recording_id"

  create_table "locations", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.float    "longitude"
    t.float    "latitude"
    t.float    "speed"
    t.float    "bearing"
    t.float    "accuracy"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "locations", ["recording_id"], name: "index_locations_on_recording_id"

  create_table "magnetic_fields", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.float    "x"
    t.float    "y"
    t.float    "z"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "magnetic_fields", ["recording_id"], name: "index_magnetic_fields_on_recording_id"

  create_table "pressures", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.float    "pressure"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "pressures", ["recording_id"], name: "index_pressures_on_recording_id"

  create_table "proximities", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.float    "proximity"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "proximities", ["recording_id"], name: "index_proximities_on_recording_id"

  create_table "recordings", force: true do |t|
    t.integer  "device_id"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "title"
    t.integer  "user_id"
  end

  add_index "recordings", ["device_id"], name: "index_recordings_on_device_id"
  add_index "recordings", ["user_id"], name: "index_recordings_on_user_id"

  create_table "rotation_vectors", force: true do |t|
    t.integer  "recording_id"
    t.datetime "time"
    t.float    "xSinTheta"
    t.float    "ySinTheta"
    t.float    "zSinTheta"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "rotation_vectors", ["recording_id"], name: "index_rotation_vectors_on_recording_id"

  create_table "users", force: true do |t|
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "users", ["email"], name: "index_users_on_email", unique: true
  add_index "users", ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true

end
